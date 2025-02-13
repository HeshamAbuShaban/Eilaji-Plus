package dev.training.eilaji_plus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.data.models.server.SubCategory
import dev.training.eilaji_plus.data.static_factory.StaticFactory
import dev.training.eilaji_plus.databinding.ItemSubCategoryBinding
import javax.inject.Inject

class SubCategoriesAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder>() {

    private var lastItemSelected: Int = 0

    private val subCategoryItems: List<SubCategory> = StaticFactory.listSubCategories

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoriesViewHolder =
        SubCategoriesViewHolder(
            ItemSubCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SubCategoriesViewHolder, position: Int) {
        val listModels = subCategoryItems[position]

        // نرسل للعنصر اذا كان تم تحديده او لا
        holder.bind(listModels, lastItemSelected == position) {
            // نحفظ موقع اخر عنصر تم تحديده قبل ان نحدثه
            val lastSelected = lastItemSelected

            // نغير موقع اخر عنصر تم تحديده حتى يصبح العنصر الاخير غير محدد عند تحديثه
            lastItemSelected = holder.adapterPosition

            // نحدث العنصر السابق حتى يخفي التحديد
            notifyItemChanged(lastSelected)

            // نحدث العنصر الذي تم تحديده
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = subCategoryItems.size

    inner class SubCategoriesViewHolder(
        private var binding: ItemSubCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context: Context = binding.root.context

        fun bind(
            model: SubCategory,
            itemIsSelected: Boolean,
            onSelected: () -> Unit
        ) {
            binding.apply {
                glide.load(model.imageUrl).into(ivSubCategories)
                tvSubCategories.text = model.title
                parentView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        if (itemIsSelected) R.color.alpha_gray else android.R.color.transparent
                    )
                )
                parentView.setOnClickListener {
                    if (!itemIsSelected) {
                        onSelected()
                    }
                }
            }
        }
    }
}
