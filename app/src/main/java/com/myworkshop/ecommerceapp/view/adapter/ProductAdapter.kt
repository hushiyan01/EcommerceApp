package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.ProductItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.product.Product
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.view.fragment.main.OnGoToProductDetailCallBack
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: List<Product>,
    private val callback: OnGoToProductDetailCallBack
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var binding: ProductItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            callback.goToProductDetailFragment(products[position])
        }
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.ivProductImg
        private val title = binding.tvProductTitle
        private val description = binding.tvProductDescription
        private val price = binding.tvProductPrice
        private var rating = 0f
        private var imageUrl = ""
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            title.text = products[position].product_name
            description.text = products[position].description
            price.text = "$ ${products[position].price}"
            rating = products[position].price.toFloat()
            imageUrl = VolleyHandler.FETCH_IMAGE_URL + products[position].product_image_url
//            VolleyImageCaching.fetchImageUsingVolley(products[position].product_image_url, )
            Picasso.get().load(imageUrl).into(imageView)
        }
    }
}