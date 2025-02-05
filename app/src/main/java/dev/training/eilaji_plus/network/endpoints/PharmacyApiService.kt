package dev.training.eilaji_plus.network.endpoints

import dev.training.eilaji_plus.data.models.server.Medicine
import dev.training.eilaji_plus.data.models.server.Pharmacy
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PharmacyApiService {
    
    @GET("pharmacies/nearby")
    suspend fun getNearbyPharmacies(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("radius") radiusKm: Double
    ): Response<List<Pharmacy>>
    
    @GET("medicines/search")
    suspend fun searchMedicines(
        @Query("query") searchTerm: String,
        @Query("categoryId") categoryId: String?
    ): Response<List<Medicine>>
}