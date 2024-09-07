package dev.training.eilaji_plus.ui.fragments.gate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dev.training.eilaji_plus.adapters.OnBoardingAdapter
import dev.training.eilaji_plus.data.static_factory.AppSharedPreferences
import dev.training.eilaji_plus.data.static_factory.StaticFactory
import dev.training.eilaji_plus.databinding.FragmentOnBoardingBinding
import dev.training.eilaji_plus.utils.UtilsAnimation
import dev.training.eilaji_plus.utils.UtilsScreen

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private val onBoardingViewModel: OBViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnBoardingPager()
        setupNextArrowButton()
        animationProgressByCurrentPageValue()
        navToLoginController()
    }

    private fun setupOnBoardingPager() {
        val paddingHorizontal = (UtilsScreen.screenWidth * 0.08).toInt()
        with(binding.onBoardingPager) {
            setPadding(paddingHorizontal, 0, paddingHorizontal, 0)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = OnBoardingAdapter()
            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(paddingHorizontal))
            })
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    onBoardingViewModel.setCurrentPage(position)
                }
            })
        }
    }

    private fun animationProgressByCurrentPageValue() {
        onBoardingViewModel.currentPage.observe(viewLifecycleOwner) { currentPage ->
            currentPage?.let {
                val isForward = currentPage > onBoardingViewModel.previousPage
                UtilsAnimation.animationProgress(
                    binding.circularProgressIndicator,
                    StaticFactory.onBoardingItems.size.toFloat(),
                    currentPage,
                    isForward
                )
                onBoardingViewModel.previousPage = currentPage
            }
        }
    }

    private fun setupNextArrowButton() {
        binding.buNextArrow.setOnClickListener {
            val currentItem = binding.onBoardingPager.currentItem + 1
            val lastItem = StaticFactory.onBoardingItems.size - 1
            if (currentItem < lastItem) {
                binding.onBoardingPager.currentItem = currentItem
            } else {
                onBoardingViewModel.reachedTheLastPage()
                binding.onBoardingPager.currentItem = 0
            }
        }
    }

    private fun navToLoginController() {
        onBoardingViewModel.navigateToLogin.observe(viewLifecycleOwner) { navigateToLogin ->
            if (navigateToLogin) {
                //Get the NavController  inside a fragment that is hosted within an activity with a NavHostFragment
                val navController = findNavController()

                // removes the onBoardingScreen of the back stack
                navController.popBackStack()


                // STOPSHIP: Make Login Fragment Next
                // TODO : Stopped Here
/*
                // navigate to the Login with making sure there is no return cause of the line above
                val directions = OnBoardingFragmentDirections.actionNavigationOnBoardingToNavigationLogin()
                navController.navigate(directions)
*/

                // TODO Fix this shit
                //Set the sheared Value to True
                AppSharedPreferences.getInstance(requireContext()).doneWithOnBoarding()
                AppSharedPreferences.getInstance(requireContext()).setHeIsFirstTimeDone()
            }
        }
    }
}