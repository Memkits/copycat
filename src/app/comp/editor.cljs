
(ns app.comp.editor
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros :refer [defcomp cursor-> <> div button span textarea input]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.style :as style]))

(def style-label {:width 100})

(defcomp
 comp-editor
 (states snippet)
 (let [state (or (:data states)
                 {:title (or (:title snippet) ""), :content (or (:content snippet) "")})]
   (div
    {:style (merge ui/flex {:padding 16})}
    (div
     {}
     (div
      {:style ui/row}
      (div {:style style-label} (<> "Title"))
      (div
       {}
       (input
        {:style style/input,
         :value (:title state),
         :placeholder "Title",
         :on {:input (fn [e d! m!] (m! (assoc state :title (:value e))))}})))
     (=< nil 8)
     (div
      {:style ui/row}
      (div {:style style-label} (<> "Content"))
      (div
       {}
       (textarea
        {:style (merge style/textarea {:min-width 600, :min-height 200}),
         :placeholder "Content",
         :value (:content state),
         :on {:input (fn [e d! m!] (m! (assoc state :content (:value e))))}}))))
    (div
     {:style (merge ui/row {:padding 16})}
     (button
      {:style (merge style/button {}),
       :inner-text "Cancel",
       :on {:click (fn [e d! m!] (d! :router/set nil))}})
     (=< 8 nil)
     (button
      {:style (merge style/button {}),
       :inner-text "Submit",
       :on {:click (fn [e d! m!]
              (if (some? snippet)
                (d! :snippet/update (merge snippet state))
                (d! :snippet/create state))
              (m! nil)
              (d! :router/set nil))}})))))