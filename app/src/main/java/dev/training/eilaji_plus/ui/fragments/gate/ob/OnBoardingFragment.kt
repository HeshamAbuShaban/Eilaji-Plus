package dev.training.eilaji_plus.ui.fragments.gate.ob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dev.training.eilaji_plus.adapters.OnBoardingAdapter
import dev.training.eilaji_plus.data.static_factory.StaticFactory
import dev.training.eilaji_plus.databinding.FragmentOnBoardingBinding
import dev.training.eilaji_plus.utils.UtilsAnimation
import dev.training.eilaji_plus.utils.UtilsScreen
import dev.training.eilaji_plus.vms.EntranceViewModel

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private val onBoardingViewModel: OBViewModel by viewModels()
    private lateinit var entranceViewModel: EntranceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        entranceViewModel = ViewModelProvider(requireActivity())[EntranceViewModel::class.java]
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
            val toShow = binding.onBoardingPager.currentItem + 1
            val lastItem = StaticFactory.onBoardingItems.size - 1
            if (toShow < lastItem) {
                binding.onBoardingPager.currentItem = toShow
            } else {
                onBoardingViewModel.reachedTheLastPage()
                binding.onBoardingPager.currentItem = 0
            }
        }
    }

    private fun navToLoginController() {
        onBoardingViewModel.navigateToLogin.observe(viewLifecycleOwner) { navigateToLogin ->
            if (navigateToLogin) {
                entranceViewModel.toLogin()
            }
        }
    }
}