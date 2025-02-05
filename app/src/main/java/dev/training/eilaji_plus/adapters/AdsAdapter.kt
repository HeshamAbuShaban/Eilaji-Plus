package dev.training.eilaji_plus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.training.eilaji_plus.data.models.server.Ad
import dev.training.eilaji_plus.databinding.ItemAdsBinding
import javax.inject.Inject

class AdsAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<AdsAdapter.AdsViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Ad>() {
        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var ads: List<Ad>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder =
        AdsViewHolder(
            ItemAdsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) =
        holder.bind(ads[position])

    override fun getItemCount(): Int = ads.size

    inner class AdsViewHolder(private var binding: ItemAdsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Ad) {
            binding.apply {
                glide.load(model.imageUrl)
                    .into(ivAds)
                tvAds.text = model.title
            }
        }
    }
}