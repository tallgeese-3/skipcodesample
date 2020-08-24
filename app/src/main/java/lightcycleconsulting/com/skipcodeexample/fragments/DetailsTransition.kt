package lightcycleconsulting.com.skipcodeexample.fragments

import android.transition.*

class DetailsTransition : TransitionSet() {
    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
                .addTransition( ChangeTransform())
                .addTransition( ChangeImageTransform())
    }
}