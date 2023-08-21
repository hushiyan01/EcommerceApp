package com.myworkshop.ecommerceapp.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentSubCategoryItemListBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.category.Subcategory
import com.myworkshop.ecommerceapp.model.remote.dto.product.ProductResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.myworkshop.ecommerceapp.presenter.ProductPresenter
import com.myworkshop.ecommerceapp.view.adapter.ProductAdapter

class SubCategoryItemListFragment : Fragment(), MVPInterfaces.Product.View,MVPInterfaces.ProductCart.View {
    private lateinit var binding: FragmentSubCategoryItemListBinding
    private lateinit var presenter: ProductPresenter
    private lateinit var productInCartPresenter: ProductCartPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubCategoryItemListBinding.inflate(inflater, container, false)
        presenter = ProductPresenter(VolleyHandler(requireContext()), this)
        productInCartPresenter = ProductCartPresenter(
            CartDao(ShoppingDBHelper(requireContext())),
            this
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subCategoryId = requireArguments().getParcelable<Subcategory>("sub_category")?.subcategory_id
        if(subCategoryId!=null){
            presenter.fetchProductsBySubCategoryId(subCategoryId = subCategoryId)
        }
    }

    override fun fetchSuccess(productResult: ProductResult) {
        val list = productResult.products
        val userId = SharedPref.getSecuredSharedPreferences(requireContext()).getString("user_id","unknown_user")?:""
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(list,requireActivity().supportFragmentManager,productInCartPresenter,userId)
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), "Fetch products failed")
    }

    override fun loadCart(products: List<CartItem>) {

    }
}