package com.myworkshop.ecommerceapp.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentCategoryBinding
import com.myworkshop.ecommerceapp.model.remote.dto.category.CategoryResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.CategoryPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.view.adapter.CategoryAdapter

class CategoryFragment(private val callBack: OnGoToSubCategoryViewPagerCallBack) : Fragment(), MVPInterfaces.Category.View{
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        presenter = CategoryPresenter(VolleyHandler(requireContext()),this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.fetchAllCategories()
    }

    override fun fetchSuccess(categoryResult: CategoryResult) {
        val categories = categoryResult.categories
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = CategoryAdapter(categories, callBack)
        }
    }

    override fun fetchFailed(errorMsg: String) {
        Toast.makeText(requireContext(), "eee",Toast.LENGTH_SHORT).show()
    }

}