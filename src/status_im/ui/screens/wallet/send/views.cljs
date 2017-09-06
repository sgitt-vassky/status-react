(ns status-im.ui.screens.wallet.send.views
  (:require-macros [status-im.utils.views :refer [defview letsubs]])
  (:require [status-im.components.react :as rn]
            [re-frame.core :as re-frame]
            [status-im.components.button.view :as button]
            [status-im.components.styles :as styles]
            [status-im.components.toolbar-new.actions :as act]
            [status-im.components.toolbar-new.view :as toolbar]
            [status-im.components.camera :as camera]
            [status-im.ui.screens.wallet.send.styles :as cst]
            [status-im.components.icons.vector-icons :as vector-icons]
            [reagent.core :as r]))

(defn toolbar-view []
  [toolbar/toolbar2 {:style cst/toolbar}
   [toolbar/nav-button (act/close-white act/default-handler)]
   [toolbar/content-title {:color :white} "Send Transaction"
    {:color styles/color-light-blue} "Step 1 of 3"]])

(defn recipient-buttons []
  [rn/view {:style cst/recipient-buttons}
   [rn/touchable-highlight {:style (cst/recipient-touchable true)}
    [rn/view {:style cst/recipient-button}
     [rn/text {:style cst/recipient-button-text}
      "Choose from Contacts"]
     [vector-icons/icon :icons/contacts {:color :white
                                         :container-style cst/recipient-icon}]]]
   [rn/touchable-highlight {:style (cst/recipient-touchable true)
                            :on-press #(rn/get-from-clipboard
                                        (fn [clipboard]
                                          (re-frame/dispatch [:choose-recipient clipboard])))}
    [rn/view {:style cst/recipient-button}
     [rn/text {:style cst/recipient-button-text}
      "Use Address from Clipboard"]]]
   [rn/touchable-highlight {:style (cst/recipient-touchable false)}
    [rn/view {:style cst/recipient-button}
     [rn/text {:style cst/recipient-button-text}
      "Browse Photos"]]]])

(defview send-transaction []
  (letsubs [camera-initialized? (r/atom false)]
    [rn/view {:style cst/wallet-container}
     [toolbar-view]
     [rn/view
      {:style cst/choose-recipient-container}
      [rn/text {:style cst/choose-recipient-label} "Choose Recipient"]]
     ;; TODO, calculate width on render
     [rn/view {:style (cst/qr-container 350)}
      #_[camera/camera {:ref #(reset! camera-initialized? true)}]]
     [recipient-buttons]]))
