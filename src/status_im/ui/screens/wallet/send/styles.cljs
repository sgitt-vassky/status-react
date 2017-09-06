(ns status-im.ui.screens.wallet.send.styles
  (:require [status-im.components.styles :as st]))

(def wallet-container
  {:flex             1
   :background-color st/color-blue2})

(def toolbar
  {:background-color st/color-blue5
   :elevation        0
   :padding-bottom 10})

(def toolbar-title-container
  {:flex           1
   :flex-direction :row
   :margin-left    6})

(def toolbar-title-text
  {:color        st/color-white
   :font-size    17
   :margin-right 4})

(def toolbar-icon
  {:width  24
   :height 24})

(def toolbar-title-icon
  (merge toolbar-icon {:opacity 0.4}))

(def toolbar-buttons-container
  {:flex-direction  :row
   :flex-shrink     1
   :justify-content :space-between
   :width           68
   :margin-right    12})

(def choose-recipient-container
  {:flex-direction :row
   :padding-top 20
   :padding-bottom 32
   :justify-content :center})

(def choose-recipient-label
  {:color :white})

(def recipient-buttons
  {:flex-direction :column
   :margin-left 28
   :margin-right 28
   :margin-top 10
   :border-radius 5
   :background-color st/color-blue5})

(def recipient-icon {:margin-right 20})

(def recipient-button
  {:flex-direction :row
   :justify-content :space-between
   :margin-top 10
   :margin-left 20
   :margin-bottom 10})

(def recipient-button-text
  {:color :white
   :font-size 17})

(defn recipient-touchable [divider?]
  (cond-> {:border-color st/color-gray-transparent-medium-light}
    divider? (assoc :border-bottom-width 1)))

(defn qr-container [max-width]
  {:width max-width
   :height max-width
   :border-radius 20
   :margin-left :auto
   :margin-right :auto
   :border-color :white
   :border-width 5})
