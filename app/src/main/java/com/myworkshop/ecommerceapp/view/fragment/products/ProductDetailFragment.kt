package com.myworkshop.ecommerceapp.view.fragment.products

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.databinding.FragmentProductDetailBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.product.Product
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.ProductDetailResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.myworkshop.ecommerceapp.presenter.ProductDetailPresenter
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter
import com.myworkshop.ecommerceapp.view.adapter.SpecificationAdapter
import com.myworkshop.ecommerceapp.view.adapter.UserReviewAdapter

class ProductDetailFragment : Fragment(),
    MVPInterfaces.ProductDetail.View,
    MVPInterfaces.ProductCart.View {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var productDetailPresenter: ProductDetailPresenter
    private lateinit var fragmentViewpagerAdapter: FragmentViewpagerAdapter
    private lateinit var productInCartPresenter: ProductCartPresenter
    private lateinit var userId: String
    private lateinit var product_: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        product_ = arguments?.getParcelable("product")!!
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        productDetailPresenter =
            ProductDetailPresenter(product_.product_id, VolleyHandler(requireContext()), this)
        productInCartPresenter = ProductCartPresenter(
            CartDao(ShoppingDBHelper(requireContext())),
            this
        )
        userId = SharedPref.getSecuredSharedPreferences(requireContext())
            .getString("logged_in_email_id", "unknown user")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDetailPresenter.fetchProductDetailById(productId = product_.product_id)
    }

    @SuppressLint("SetTextI18n")
    override fun fetchSuccess(productDetailResult: ProductDetailResult) {
        val product = productDetailResult.product
        val productTitle = product.product_name
        val rating = product.average_rating.toFloat()
        val description = product.description
        val imageFrags = product.images.sortedBy { it.display_order }
            .map { ProductImageFragment(VolleyHandler.FETCH_IMAGE_URL + it.image) }
        val price = product.price
        val specifications =
            productDetailResult.product.specifications.sortedBy { it.display_order }
        val reviews = productDetailResult.product.reviews.sortedBy { it.review_date }

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
                adapter = SpecificationAdapter(specifications)
            }

            rvUserReviews.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = UserReviewAdapter(reviews)
            }

            btnProductDetailAddToCart.setOnClickListener {
                if (productInCartPresenter.isInCart(product_.product_id)) {
                    productInCartPresenter.productPlus1(product.product_id)
                } else {
                    productInCartPresenter.insertNewItem(
                        cartItem = CartItem(
                            id = product_.product_id.toLong(),
                            userId = userId,
                            itemTitle = product_.product_name,
                            price = product_.price.toFloat(),
                            img = product_.product_image_url,
                            description = product_.description
                        )
                    )
                }

            }
//            TabLayoutMediator(tabLayout,viewPager2){tab,_->
//                tab.customView = createTabView()
//            }.attach()
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), "fetch product detail failed!")
    }

    override fun loadCart(products: List<CartItem>) {
        TODO("Not yet implemented")
    }

}