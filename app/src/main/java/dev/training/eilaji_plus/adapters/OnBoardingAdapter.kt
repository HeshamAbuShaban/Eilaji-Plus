package dev.training.eilaji_plus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.training.eilaji_plus.data.models.fixed.OnBoardingItem
import dev.training.eilaji_plus.data.static_factory.StaticFactory
import dev.training.eilaji_plus.databinding.ItemOnBoardingBinding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val onBoardingItems = StaticFactory.onBoardingItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder =
        OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }

    override fun getItemCount(): Int = onBoardingItems.size

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnBoardingItem) {
            with(binding) {
                ivOnBoarding.setImageResource(item.image)
                tvOnBoarding.text = item.title
            }
        }
    }
}
