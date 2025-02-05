package dev.training.eilaji_plus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.training.eilaji_plus.data.models.server.Pharmacy
import dev.training.eilaji_plus.databinding.ItemPharmacyLocationBinding
import javax.inject.Inject

class PharmaciesLocationsAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<PharmaciesLocationsAdapter.PharmaciesLocationsViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Pharmacy>() {
        override fun areItemsTheSame(oldItem: Pharmacy, newItem: Pharmacy): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Pharmacy, newItem: Pharmacy): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffUtil)

    var pharmacies: List<Pharmacy>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    private lateinit var navigateToChat: (model: Pharmacy) -> Unit

    fun setOnChatClickedListener(listener: (model: Pharmacy) -> Unit) {
        navigateToChat = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PharmaciesLocationsViewHolder =
        PharmaciesLocationsViewHolder(
            ItemPharmacyLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PharmaciesLocationsViewHolder, position: Int) =
        holder.bind(pharmacies[position])

    override fun getItemCount(): Int = pharmacies.size

    inner class PharmaciesLocationsViewHolder(private var binding: ItemPharmacyLocationBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(model: Pharmacy) {
            binding.apply {

                glide.load(model.pharmacy_image_url)
                    .into(ivPharmacyLocation)

                tvPharmacyNameLocation.text = model.pharmacy_name
                tvPharmacyDistanceLocation.text = model.address

                buPharmacyChatLocation.setOnClickListener { navigateToChat(model) }
            }
        }
    }
}