package com.myworkshop.ecommerceapp.view.adapter

//import com.myworkshop.ecommerceapp.view.fragment.main.OnGoToProductDetailCallBack
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.CategoryItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.category.Category
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.view.fragment.main.SubCategoryFragment
import com.squareup.picasso.Picasso

class CategoryAdapter(
    private val categories: List<Category>,
//    private val callBack: OnGoToSubCategoryViewPagerCallBack,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {
    private lateinit var binding: CategoryItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            fragmentManager.beginTransaction().replace(
                R.id.fg_home_container, SubCategoryFragment(
                    id = categories[position].category_id,
                    toolBarTitle = categories[position].category_name
                )
            )
                .addToBackStack("sub_category_fragment")
                .commit()
        }
    }

    inner class CategoryItemViewHolder(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var imgView = binding.ivCategoryItemImg
        private var title = binding.tvCategoryItemTitle
        fun bind(position: Int) {
            val imgUrl = VolleyHandler.FETCH_IMAGE_URL + categories[position].category_image_url

            Picasso.get().load(imgUrl).into(imgView)
            title.text = categories[position].category_name
        }
    }
}