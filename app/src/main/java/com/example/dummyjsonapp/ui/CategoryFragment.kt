package com.example.dummyjsonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyjsonapp.Adapter.CategoryAdapter
import com.example.dummyjsonapp.MyApplication
import com.example.dummyjsonapp.database.DBHelper
import com.example.dummyjsonapp.databinding.FragmentCategoryBinding
import com.example.dummyjsonapp.domain.NetworkModule
import com.example.dummyjsonapp.domain.category.CategoryService
import com.example.dummyjsonapp.entity.Category
import com.example.dummyjsonapp.viewModel.CategoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null
    private val categoryViewModel: CategoryViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.categoryFragment = this
        binding?.viewModel = categoryViewModel
        getCategoryDataFromDB()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun getCategoryData() {
        val retService : CategoryService = NetworkModule.getRetrofitInstance().create(
            CategoryService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retService.getCategories()
            val categories: ArrayList<String>? = response.body()
            response.body().let {
                val categoryList: ArrayList<Category> = ArrayList()
                for (res in it!!) {
                    val category = Category(res)
                    categoryList.add(category)

                    CoroutineScope(Dispatchers.Main).launch {
                        binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        binding?.recyclerview?.adapter = CategoryAdapter(categoryList)
                    }
                }
                println("qq -> $response")
            }
            println("qq -> $response")
        }
    }

    fun getCategoryDataFromDB() {
        val db = DBHelper(requireContext(), null)
        val categoryList = db.getCategoryList()

        CoroutineScope(Dispatchers.Main).launch {
            binding?.recyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding?.recyclerview?.adapter = CategoryAdapter(categoryList)
        }
    }


}