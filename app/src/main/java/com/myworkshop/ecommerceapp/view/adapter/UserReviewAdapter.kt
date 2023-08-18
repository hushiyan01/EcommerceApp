package com.myworkshop.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.UserReviewItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.Review

class UserReviewAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<UserReviewAdapter.ReviewViewHolder>() {
    private lateinit var binding: UserReviewItemBinding

    inner class ReviewViewHolder(binding: UserReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val userName = binding.tvUserName
        private val title = binding.tvUserReviewTitle
        private val reviewContent = binding.tvUserReviewContent
        private val rating = binding.rbUserRating

        fun bind(position: Int) {
            userName.text = reviews[position].full_name
            title.text = reviews[position].review_title
            reviewContent.text = reviews[position].review
            rating.rating = reviews[position].rating.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = UserReviewItemBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(position)
    }
}