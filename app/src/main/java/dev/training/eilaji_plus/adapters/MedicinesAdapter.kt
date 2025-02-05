package dev.training.eilaji_plus.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.data.models.server.Medicine
import dev.training.eilaji_plus.databinding.ItemMedicineBinding
import javax.inject.Inject

class MedicinesAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<MedicinesAdapter.MedicinesViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Medicine>() {
        override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean =
            oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, differCallback)

    var medicine: List<Medicine>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    private var isGridLayout: Boolean = false
    private var halfScreenWidth: Int = 0

    fun setGridLayout(isGridLayout: Boolean) {
        this.isGridLayout = isGridLayout
    }

    fun setHalfScreenWidth(halfScreenWidth: Int) {
        this.halfScreenWidth = halfScreenWidth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicinesViewHolder =
        MedicinesViewHolder(
            ItemMedicineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MedicinesViewHolder, position: Int) {
        holder.bind(medicine[position], position)
    }

    override fun getItemCount(): Int {
        return medicine.size
    }


    inner class MedicinesViewHolder(private var binding: ItemMedicineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: Medicine, position: Int) {
            if (isGridLayout) {
                binding.root.layoutParams.width = halfScreenWidth

                if (position == 0 || position == 1) {
                    // margin top first tow item
                    binding.root.setPadding(0, 60, 0, 0)
                }
            }

            binding.apply {
                glide.load(model.imageUrl).into(ivMedicine)
                tvMedicineName.text = model.title
                tvMedicineSalary.text = "${model.price}$"

                setUpFavoriteIcon(model)

                buAddMedicineToFavorite.setOnClickListener {
                    setUpFavoriteIcon(model)
                }
            }
        }

        private fun setUpFavoriteIcon(model: Medicine) {
            if (model.isFavorite) {
                binding.buAddMedicineToFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                binding.buAddMedicineToFavorite.setImageResource(R.drawable.ic_favorite_border)
            }
        }
    }
}