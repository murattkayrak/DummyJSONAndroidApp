package com.example.dummyjsonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dummyjsonapp.R
import com.example.dummyjsonapp.database.DBHelper
import com.example.dummyjsonapp.viewModel.HomeViewModel
import com.example.dummyjsonapp.databinding.FragmentStartBinding
import com.example.dummyjsonapp.domain.NetworkModule
import com.example.dummyjsonapp.domain.category.CategoryService
import com.example.dummyjsonapp.domain.product.ProductService
import com.example.dummyjsonapp.entity.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeFragment = this
        binding?.viewModel = homeViewModel
        //getData()
        //getProductData()
        //getCategoryData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun navigatetoCategories() {
        findNavController().navigate(R.id.action_startFragment_to_categoryFragment)
    }

    fun navigatetoProducts() {
        findNavController().navigate(R.id.action_startFragment_to_productFragment)
    }

    fun getData() {
        getCategoryData()
        getProductData()
    }

    fun getCategoryData() {
        val retService : CategoryService = NetworkModule.getRetrofitInstance().create(CategoryService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getCategories()
            response.body()?.let {
                for (res in it) {
                    val category = Category(res)
                    val db = DBHelper(requireContext(), null)
                    db.addCategory(category.name!!)
                }
                println("qq -> $response")
            }
            println("qq -> $response")
        }
    }

    fun getProductData() {
        val retService : ProductService = NetworkModule.getRetrofitInstance().create(ProductService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getProducts()
            response.body()?.products?.let {
                for (product in it) {
                    val db = DBHelper(requireContext(), null)
                    db.addProduct(product)

                    //
                }
                //
            }

            val qq = response.body()
            println("qq -> $response")
        }

        //
    }

    /**
     * Start an order with the desired quantity of cupcakes and navigate to the next screen.
     */
//    /*
    fun orderCupcake(quantity: Int) {
        // Update the view model with the quantity
//        sharedViewModel.setQuantity(quantity)

        // If no flavor is set in the view model yet, select vanilla as default flavor
//        if (sharedViewModel.hasNoFlavorSet()) {
//            sharedViewModel.setFlavor(getString(R.string.vanilla))
//        }

        // Navigate to the next destination to select the flavor of the cupcakes
        Toast.makeText(requireContext(), quantity.toString(), Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_startFragment_to_categoryFragment)
    }
//    */
}