(ns status-im.components.button.view
  (:require [status-im.components.button.styles :as button.styles]
            [status-im.components.react :as rn]
            [status-im.utils.platform :as p]))

(defn button [{:keys [on-press style text text-style disabled?]
               :or {style button.styles/action-button}}]
  [rn/touchable-highlight (merge {:style button.styles/button-container} (when (and on-press (not disabled?)) {:on-press on-press}))
   [rn/view {:style style}
    [rn/text {:style      (or text-style
                              (if disabled? button.styles/action-button-text-disabled button.styles/action-button-text))
              :font       :medium
              :uppercase? p/android?}
     text]]])

(defn primary-button [m]
  (button (merge {:style button.styles/primary-button :text-style button.styles/primary-button-text} m)))

(defn secondary-button [m]
  (button (merge {:style button.styles/secondary-button :text-style button.styles/secondary-button-text} m)))

(defn- first-or-last [i v] (or (zero? i) (= i (dec (count v)))))

(defn- button-style [i v] (if (first-or-last i v) button.styles/action-button button.styles/action-button-center))

(defn buttons
  ([v] (buttons {} v))
  ([m v]
   [rn/view {:style (merge button.styles/action-buttons-container m)}
    (doall
      (map-indexed
        (fn [i m] ^{:key i} [button (merge m {:style (button-style i v)})])
        v))]))