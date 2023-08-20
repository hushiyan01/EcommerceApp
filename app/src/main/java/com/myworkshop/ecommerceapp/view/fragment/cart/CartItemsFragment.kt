package com.myworkshop.ecommerceapp.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.myworkshop.ecommerceapp.databinding.FragmentCartItemsBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.myworkshop.ecommerceapp.view.adapter.CartCheckoutAdapter
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo

class CartItemsFragment(val updateCheckoutInfo: UpdateCheckoutInfo) : Fragment(), MVPInterfaces.ProductCart.View {
    private lateinit var binding: FragmentCartItemsBinding
    private lateinit var presenter: ProductCartPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartItemsBinding.inflate(inflater, container, false)
        presenter = ProductCartPresenter(CartDao(ShoppingDBHelper(requireContext())), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchProductsInCart()
        binding.btnNext.setOnClickListener {
            val checkoutViewPager2 = findViewPagerParent(view)
            if (checkoutViewPager2 != null) {
                val currentItem = checkoutViewPager2.currentItem
                val nextPage = currentItem + 1

                if (checkoutViewPager2.adapter != null && nextPage < checkoutViewPager2.adapter!!.itemCount) {
                    checkoutViewPager2.setCurrentItem(nextPage, true)
                }
            }
        }
    }

    override fun loadCart(products: List<CartItem>) {
        binding.rvCartItemCheckoutList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CartCheckoutAdapter(products)
        }
        binding.tvTotalPrice.text = products.map { it.price * it.num }.sum().toString()
        updateCheckoutInfo.updateCartItems(products)
    }

    private fun findViewPagerParent(view: View): ViewPager2? {
        var parentView: ViewParent? = view.parent

        while (parentView != null) {
            if (parentView is ViewPager2) {
                return parentView
            }
            parentView = parentView.parent
        }

        return null
    }

}