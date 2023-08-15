package com.myworkshop.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.CategoryItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.category.Category
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.model.remote.util.VolleyImageCaching
import com.myworkshop.ecommerceapp.view.fragment.main.OnGoToSubCategoryViewPagerCallBack

class CategoryAdapter(private val categories:List<Category>, private val callBack: OnGoToSubCategoryViewPagerCallBack):RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {
    private lateinit var binding: CategoryItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener{
            callBack.go(categories[position].category_id)
        }
    }
    inner class CategoryItemViewHolder(binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root){
        private var imgView = binding.ivCategoryItemImg
        private var title = binding.tvCategoryItemTitle
        fun bind(position: Int){
            val imgUrl = categories[position].category_image_url
//            imgView.setImageDrawable(ContextCompat.getDrawable(imgView.context, R.drawable.ic_launcher_background))
            VolleyImageCaching.fetchImageUsingVolley(imgUrl, imgView, R.drawable.ic_launcher_background, R.color.black)
            title.text = categories[position].category_name
        }
    }
}