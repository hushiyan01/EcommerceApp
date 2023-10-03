package com.myworkshop.ecommerceapp.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentCategoryBinding
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.product.SearchProductResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.CategoryPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.SearchProductPresenter
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.CategoryAdapter

class CategoryFragment : Fragment(), MVPInterfaces.Category.View, MVPInterfaces.SearchProduct.View {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryPresenter: CategoryPresenter
    private lateinit var searchProductPresenter: SearchProductPresenter
    private lateinit var fragmentManager: FragmentManager
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val volleyHandler = VolleyHandler(requireContext())
        categoryPresenter = CategoryPresenter(volleyHandler, this)
        searchProductPresenter = SearchProductPresenter(volleyHandler, this)
        fragmentManager = requireActivity().supportFragmentManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryPresenter.fetchAllCategories()
        val mainActivity = activity
        if (mainActivity != null && mainActivity is MainActivity) {
            mainActivity.changeToolbar(this, "Super Cart")
        }
        requireActivity().findViewById<AppCompatButton>(R.id.btn_search_g).apply {
            isEnabled = true
            val searchBar = requireActivity().findViewById<ConstraintLayout>(R.id.search_bar_section)
            setOnClickListener {
                if(searchBar.visibility == View.VISIBLE){
                    searchBar.visibility = View.GONE
                }else{
                    searchBar.visibility = View.VISIBLE
                }
            }
        }
        binding.apply {
            btnSearch.setOnClickListener {
                val keyword = etKeyWordSearch.text.toString()
                searchProductPresenter.searchProduct(keyword)
            }
            btnCancelSearch.setOnClickListener {
                etKeyWordSearch.text?.clear()
                rvCategories.visibility = View.VISIBLE
                rvProducts.visibility = View.GONE
            }
        }
        navController = findNavController()
    }

    override fun fetchSuccess(categoryResult: CategoryResult) {
        val categories = categoryResult.categories
        binding.rvProducts.visibility = View.GONE
        binding.rvCategories.visibility = View.VISIBLE
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = CategoryAdapter(categories, fragmentManager,navController)
        }
    }

    override fun onStop() {
        super.onStop()
        requireActivity().findViewById<AppCompatButton>(R.id.btn_search_g).isEnabled = false
    }

    override fun fetchFailed(errorMsg: String) {
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun searchSuccess(searchProductResult: SearchProductResult) {

    }

    override fun searchFailed(errorMsg: String) {
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }

}