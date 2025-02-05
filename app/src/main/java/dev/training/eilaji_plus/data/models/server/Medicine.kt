package dev.training.eilaji_plus.data.models.server

data class Medicine(
    val id: String,
    val imageUrl: String,
    val title: String,
    val price: Double,
    val details: String,
    val alternativesMedicine: ArrayList<String>,
    val idCategory: String,
    val idSubCategory: String,
    val isFavorite: Boolean
)