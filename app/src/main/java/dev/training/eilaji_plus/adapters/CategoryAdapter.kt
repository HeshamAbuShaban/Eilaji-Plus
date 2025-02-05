package dev.training.eilaji_plus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.training.eilaji_plus.data.models.server.Category
import dev.training.eilaji_plus.databinding.ItemCategoryBinding
import javax.inject.Inject

class CategoryAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var categories: List<Category>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private lateinit var navToSubListener: (categoryId: String, categoryTitle: String) -> Unit
    fun setOnNavToSubListener(listener: (categoryId: String, categoryTitle: String) -> Unit) {
        navToSubListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, position, categories.size)
    }

    inner class CategoryViewHolder(private var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: Category,
            position: Int,
            listSize: Int
        ) {
            binding.apply {
                if (position == 0 || position == 1) {
                    // margin top first tow item
                    parentCategoryItem.setPadding(0, 60, 0, 0)
                } else if (position == listSize - 2 || position == listSize - 1) {
                    // margin bottom last tow item
                    parentCategoryItem.setPadding(0, 0, 0, 222)
                }

                glide
                    .load(model.imageUrl)
                    .into(ivPharmacyDepartment)

                tvPharmacyDepartment.text = model.title

                parentCardCategoryItem.setOnClickListener {
                    navToSubListener(model.id, model.title)
                }
            }
        }
    }

}