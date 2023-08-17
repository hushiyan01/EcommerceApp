package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.CartProductItemBinding
import com.myworkshop.ecommerceapp.databinding.CheckoutItemBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.dto.product.Product
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.squareup.picasso.Picasso

class CartCheckoutAdapter(
    val products:List<CartItem>
):RecyclerView.Adapter<CartCheckoutAdapter.CartProductViewHolder>() {
    private lateinit var binding:CheckoutItemBinding
    inner class CartProductViewHolder(binding: CheckoutItemBinding):RecyclerView.ViewHolder(binding.root){
        val title = binding.tvCartItemCheckoutTitle
        val unitPrice = binding.tvCartItemCheckoutUnitPrice
        val num = binding.tvCartItemQuantity
        val img = binding.ivProductImg
        val amount = binding.tvCartItemTotalPrice

        @SuppressLint("SetTextI18n")
        fun bind(position:Int){
            title.text = products[position].itemTitle
            unitPrice.text = "$ ${products[position].price}"
            num.text = products[position].num.toString()
            amount.text = (products[position].price * products[position].num).toString()
            val imageUrl = VolleyHandler.FETCH_IMAGE_URL + products[position].img
            Picasso.get().load(imageUrl).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CheckoutItemBinding.inflate(inflater,parent,false)
        return CartProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(position)
    }
}