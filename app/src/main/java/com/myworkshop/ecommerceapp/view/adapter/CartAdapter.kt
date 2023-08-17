package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.CartProductItemBinding
import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.dto.product.Product
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.ProductCartPresenter
import com.squareup.picasso.Picasso

class CartAdapter(val products:List<CartItem>,private var presenter: ProductCartPresenter):RecyclerView.Adapter<CartAdapter.CartProductViewHolder>() {
    private lateinit var binding:CartProductItemBinding
    inner class CartProductViewHolder(binding: CartProductItemBinding):RecyclerView.ViewHolder(binding.root){
        val title = binding.tvProductTitle
        val unitPrice = binding.tvProductPrice
        val num = binding.tvProductNum
        val desc = binding.tvProductDescription
        val img = binding.ivProductImg

        @SuppressLint("SetTextI18n")
        fun bind(position:Int){
            title.text = products[position].itemTitle
            unitPrice.text = "$ ${products[position].price}"
            desc.text = products[position].description
            num.text = products[position].num.toString()
            val imageUrl = VolleyHandler.FETCH_IMAGE_URL + products[position].img
            Picasso.get().load(imageUrl).into(img)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CartProductItemBinding.inflate(inflater,parent,false)
        return CartProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(position)
        binding.apply {
            btnAdd1.setOnClickListener {
                products[position].num++
                notifyItemChanged(position)
                presenter.productPlus1(products[position].id.toString())
            }
            btnRemove1.setOnClickListener {
                if(products[position].num>=1){
                    products[position].num--
                    notifyItemChanged(position)
                    presenter.productMinus1(products[position].id.toString())
                }else{
                    presenter.remove(id = products[position].id.toString())
                }
            }
        }
    }
}