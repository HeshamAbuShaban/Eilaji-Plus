package dev.training.eilaji_plus.ui.fragments.user_interface.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.training.eilaji_plus.adapters.AdsAdapter
import dev.training.eilaji_plus.adapters.SubCategoriesAdapter
import dev.training.eilaji_plus.data.models.server.Ad
import dev.training.eilaji_plus.databinding.FragmentHomeBinding
import dev.training.eilaji_plus.utils.views.DepthPageTransformer
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var adsAdapter: AdsAdapter
    @Inject
    lateinit var subCategoriesAdapter: SubCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupListeners()
        fetchAds()
        displayAds()
        setupSubCategoriesRec()
    }

    override fun onStart() {
        super.onStart()
        startShimmers()
    }

    private fun startShimmers() {
        with(binding) {
            shimmerAdContainer.startShimmer()
            shimmerMedContainer.startShimmer()
            shimmerCategoriesPharmaceuticalsContainer.startShimmer()
        }
    }

    private fun setupListeners() {
        binding.searchViewListener.setOnClickListener {
            /*@Deprecated
            with(requireActivity().window){
                enterTransition = Explode()
                exitTransition = Explode()
            }*/

            /*val intent = Intent(requireContext(), AlternativesActivity::class.java)
            intent.putExtra(
                "fragmentType",
                FragmentsKeys.search.name
            ) // Set the fragment type as "search" or "map"
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())*/
        }

        // Temp Move to medicine fragment
        binding.buShowAllBestSeller.setOnClickListener {
            /* val intent = Intent(requireContext(), AlternativesActivity::class.java)
             intent.putExtra("fragmentType", FragmentsKeys.medicine.name)
             startActivity(intent)*/
        }

    }

    private fun displayAds() {
        /*homeViewModel.adsList.observe(viewLifecycleOwner) { adsList ->
            removeAdsShimmer()
            setupAdsPager(adsList)
        }*/
        val adsList = emptyList<Ad>()
        setupAdsPager(adsList)
        removeAdsShimmer()
    }

    private fun removeAdsShimmer() {
        with(binding.shimmerAdContainer) {
            stopShimmer()
            visibility = View.GONE
        }
    }

    /*private fun removeMedShimmer() {
        with(binding.shimmerMedContainer){
            stopShimmer()
            val isVisible = isVisible
            visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }*/
    /*private fun removeCMShimmer() {
        with(binding.shimmerCategoriesPharmaceuticalsContainer){
            stopShimmer()
            val isVisible = isVisible
            visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }*/

    private fun setupAdsPager(adsList: List<Ad>) {
        with(binding.pagerAds) {
            adapter = adsAdapter.apply {
                this.ads = adsList
            }
            setPageTransformer(DepthPageTransformer())
            /*indicatorAds.setupViewPager2(this, adsList.size, 0)*/
        }
    }

    //this methods gets the ads from server
    private fun fetchAds() {
        // TODO:Redo it with REST Backend instead of Firebase
        /*adsRef.get().addOnSuccessListener { querySnapshot ->
            val adList: ArrayList<Ad> = ArrayList()
            for (documentSnapshot in querySnapshot) {
                val ad = documentSnapshot.toObject(Ad::class.java)
                adList.add(ad)
            }
            homeViewModel.setAdsList(adList)
            //Todo: Stop the Shimmer
            removeAdsShimmer()
        }.addOnFailureListener { exception ->
            Log.e("HomeFragment", "fetchAds: exc", exception)
            Log.d("HomeFragment", "fetchAds: massage" + exception.localizedMessage)
        }*/
    }

    private fun setupSubCategoriesRec() {
        with(binding.recyclerCategoriesPharmaceuticals) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = subCategoriesAdapter
        }
    }

// get the  ("Medicines") Categories
    /*private fun fetchPharmaceuticals() {
        val categoryID = "Y5JJ*JYQyk*k*C*baxy7ZOa4"

        // Query the SubCategories collection to filter based on the category ID
        val subCategoriesRef = FirebaseFire-store.getInstance().collection("SubCategories")
        val subCategoriesQuery = subCategoriesRef.whereEqualTo("idCategory", categoryID)

        subCategoriesQuery.get()
            .addOnSuccessListener { subCategoriesQuerySnapshot ->
                // Process the filtered subcategories
                val subCategoryIDs = subCategoriesQuerySnapshot.documents.map { it.id }

                // Query the Medicines collection to filter based on the filtered subcategory IDs
                val medicinesRef = FirebaseFire-store.getInstance().collection("Medicines")
                val medicinesQuery = medicinesRef.whereIn("idSubCategory", subCategoryIDs)

                medicinesQuery.get()
                    .addOnSuccessListener { medicinesQuerySnapshot ->
                        // Process the filtered medicines
                        for (documentSnapshot in medicinesQuerySnapshot) {
                            val medicine = documentSnapshot.toObject(Medicine::class.java)
                            // Handle each medicine as needed
                            Log.i(TAG, "fetchPharmaceuticals: Medicine: $medicine")
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Handle any errors that occurred during the query for medicines
                        Log.e(TAG, "fetchPharmaceuticals: e", exception)
                    }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occurred during the query for subcategories
                Log.e(TAG, "fetchPharmaceuticals: ex", exception)
            }
    }*/

    /*private fun setupBestSellerRecycler(medicineList: ArrayList<Medicine>) {
        with(binding.recyclerBestSeller) {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = MedicinesAdapter(medicineList)
        }
    }*/

    /*private fun fetchBestSellerMedicines() {

    }*/

}