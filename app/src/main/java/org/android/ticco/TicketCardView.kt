package org.android.ticco

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.android.ticco.databinding.WidgetTicketCardBinding

class TicketCardView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributesSet, defStyleAttr) {

    private var frontAnimation: AnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.cardflip_right_in) as AnimatorSet
    private var backAnimation: AnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.cardflip_right_out) as AnimatorSet
    private var isFront: Boolean = true

    private var binding: WidgetTicketCardBinding =
        WidgetTicketCardBinding.inflate(LayoutInflater.from(context))

    init {
        filpCard()
    }

    private fun filpCard() {
        binding.root.setOnClickListener {
            if (isFront) {
                frontAnimation.setTarget(binding.cardViewFront)
                backAnimation.setTarget(binding.cardViewBack)
                frontAnimation.start()
                backAnimation.start()
                isFront = false
            } else {
                frontAnimation.setTarget(binding.cardViewBack)
                backAnimation.setTarget(binding.cardViewFront)
                frontAnimation.start()
                backAnimation.start()
                isFront = true
            }
        }
    }

}