package dev.training.eilaji_plus.utils

import android.animation.ValueAnimator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlin.math.roundToInt

object UtilsAnimation {
    fun animationProgress(
        indicator: CircularProgressIndicator,
        pageNum: Float,
        currentPage: Int,
        isForward: Boolean
    ) {
        val max = indicator.max
        val value = max / pageNum
        // بنزود 2 علشان لما نقدم بنكون راجعين 1 + لما نعمل رجوع بنرجع كمان واحد
        val startValue = value * if (isForward) currentPage else currentPage + 2
        val animator = ValueAnimator.ofFloat(value)
        animator.interpolator = LinearOutSlowInInterpolator()
        animator.duration = 300
        animator.addUpdateListener { valueAnimator: ValueAnimator ->
            val animValue = valueAnimator.animatedValue as Float
            val progress = (startValue + if (isForward) animValue else -animValue).roundToInt()
            indicator.progress = progress
        }
        animator.start()
    }
}