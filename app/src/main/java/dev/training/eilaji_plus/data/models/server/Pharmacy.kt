package dev.training.eilaji_plus.data.models.server

data class Pharmacy(
    var uid: String,
    var pharmacy_image_url: String,
    var pharmacy_name: String,
    var phone: String,
    var address: String,
    var lat: Double,
    var lng: Double,
    var token: String
)