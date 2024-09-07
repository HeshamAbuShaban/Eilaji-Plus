package dev.training.eilaji_plus.data.static_factory

import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.data.models.fixed.OnBoardingItem

object StaticFactory {

    val onBoardingItems = listOf(
        OnBoardingItem(
            R.drawable.on_boarding_1,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
        OnBoardingItem(
            R.drawable.on_boarding_2,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
        OnBoardingItem(
            R.drawable.on_boarding_3,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
    )
}