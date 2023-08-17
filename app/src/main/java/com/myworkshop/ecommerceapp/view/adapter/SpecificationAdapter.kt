package com.myworkshop.ecommerceapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.SpecificationItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.product_detail.Specification

class SpecificationAdapter(private val specifications: List<Specification>) :
    RecyclerView.Adapter<SpecificationAdapter.SpecificationViewHolder>() {
    private lateinit var binding: SpecificationItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = SpecificationItemBinding.inflate(layoutInflater, parent, false)
        return SpecificationViewHolder(binding)
    }

    override fun getItemCount(): Int = specifications.size
    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class SpecificationViewHolder(val binding: SpecificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.tvSpecificationTitle
        private val description = binding.tvSpecificationDescription
        fun bind(position: Int) {
            title.text = specifications[position].title
            description.text = specifications[position].specification
        }
    }
}