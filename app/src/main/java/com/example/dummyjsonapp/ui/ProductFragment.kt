package com.example.dummyjsonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyjsonapp.Adapter.CategoryAdapter
import com.example.dummyjsonapp.Adapter.ProductAdapter
import com.example.dummyjsonapp.database.DBHelper
import com.example.dummyjsonapp.databinding.FragmentProductBinding
import com.example.dummyjsonapp.domain.NetworkModule
import com.example.dummyjsonapp.domain.product.ProductService
import com.example.dummyjsonapp.viewModel.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private var binding: FragmentProductBinding? = null
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentProductBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.productFragment = this
        binding?.viewModel = productViewModel
        getProductDataFromDB()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun getProductData() {
        val retService : ProductService = NetworkModule.getRetrofitInstance().create(ProductService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getProducts()
            val result = response.body()
            result?.products?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding?.recyclerview?.adapter = ProductAdapter(it.toMutableList())
                }
            }
        }
    }

    fun getProductDataFromDB() {
        val db = DBHelper(requireContext(), null)
        val productList = db.getProductList()

        CoroutineScope(Dispatchers.Main).launch {
            binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding?.recyclerview?.adapter = ProductAdapter(productList)
        }
    }
}