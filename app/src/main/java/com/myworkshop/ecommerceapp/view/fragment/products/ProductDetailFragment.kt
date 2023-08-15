package com.myworkshop.ecommerceapp.view.fragment.products

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentProductDetailBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductDetailPresenter
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter

class ProductDetailFragment(private val productId:String) : Fragment(), MVPInterfaces.ProductDetail.View {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var presenter:ProductDetailPresenter
    private lateinit var fragmentViewpagerAdapter: FragmentViewpagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        presenter = ProductDetailPresenter(productId, VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchProductDetailById(productId)
    }

    @SuppressLint("SetTextI18n")
    override fun fetchSuccess(productDetailResult: ProductDetailResult) {
        val product = productDetailResult.product

        val productTitle = product.product_name
        val rating = product.average_rating.toFloat()
        val description = product.description
        val imageFrags = product.images.sortedBy { it.display_order }.map { ProductImageFragment(VolleyHandler.FETCH_IMAGE_URL+it.image) }
        val price = product.price

        fragmentViewpagerAdapter = FragmentViewpagerAdapter(
            imageFrags,
            requireActivity()
        )

        binding.apply {
            tvProductDetailTitle.text = productTitle
            tvProductDetailDescription.text = description
            tvProductDetailPrice.text = "$ $price"
            rbProductDetailRating.rating = rating
            vpProductDetailSpecificationImage.adapter = fragmentViewpagerAdapter

            rvSpecifications.apply {
                layoutManager = LinearLayoutManager(requireContext())
//                adapter =
            }
//            TabLayoutMediator(tabLayout,viewPager2){tab,_->
//                tab.customView = createTabView()
//            }.attach()
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(),"fetch product detail failed!")
    }

}