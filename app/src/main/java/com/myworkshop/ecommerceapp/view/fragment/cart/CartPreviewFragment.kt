package com.myworkshop.ecommerceapp.view.fragment.cart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentCartPreviewBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.myworkshop.ecommerceapp.view.adapter.CartAdapter
import com.myworkshop.ecommerceapp.view.adapter.OnCartItemChangeCallback
import com.myworkshop.ecommerceapp.view.fragment.checkout.CheckOutFragment

class CartPreviewFragment : Fragment(),MVPInterfaces.ProductCart.View,OnCartItemChangeCallback {
    private lateinit var binding: FragmentCartPreviewBinding
    private lateinit var cartDao: CartDao
    private lateinit var fragmentManager: FragmentManager
    private lateinit var presenter: ProductCartPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartPreviewBinding.inflate(layoutInflater, container, false)
        cartDao = CartDao(ShoppingDBHelper(requireContext()))
        presenter = ProductCartPresenter(cartDao, this)
        fragmentManager = requireActivity().supportFragmentManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fetchProductsInCart()
        binding.btnCheckout.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.fg_home_container, CheckOutFragment())
                .addToBackStack("checkout_fragment")
                .commit()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun loadCart(products: List<CartItem>) {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CartAdapter(products,presenter,this@CartPreviewFragment)
        }
        binding.tvTotalPrice.text = "$ ${products.map { it.price*it.num }.sum()}"
    }

    @SuppressLint("SetTextI18n")
    override fun updateTotalPrice(totalPrice:Float) {
        binding.tvTotalPrice.text = "$ $totalPrice"
    }

}