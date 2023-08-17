package com.myworkshop.ecommerceapp.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentSubCategoryItemListBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.remote.dto.category.Subcategory
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductPresenter
import com.myworkshop.ecommerceapp.view.adapter.ProductAdapter

class SubCategoryItemListFragment(
    private val subCategory: Subcategory,
    private val callBack: OnGoToProductDetailCallBack
) : Fragment(), MVPInterfaces.Product.View {
    private lateinit var binding: FragmentSubCategoryItemListBinding
    private lateinit var presenter: ProductPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubCategoryItemListBinding.inflate(inflater, container, false)
        presenter = ProductPresenter(VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchProductsBySubCategoryId(subCategoryId = subCategory.subcategory_id)
    }

    override fun fetchSuccess(productResult: ProductResult) {
        val list = productResult.products
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(list, callBack)
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), "Fetch products failed")
    }


}