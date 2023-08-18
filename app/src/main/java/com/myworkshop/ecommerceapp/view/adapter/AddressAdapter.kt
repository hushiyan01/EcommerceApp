package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.databinding.AddressItemBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView

class AddressAdapter(
    val addressViews: List<AddressView>,
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    private lateinit var binding: AddressItemBinding

    inner class AddressViewHolder(binding: AddressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val type = binding.tvAddressType
        private val address = binding.tvAddress
        var selectorButton = binding.btnAddressSelector
        var isPressed = false

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            type.text = addressViews[position].type
            address.text = addressViews[position].address
            isPressed = addressViews[position].isSelected
            selectorButton.isPressed = isPressed
            selectorButton
                .setOnClickListener {
                    if (!addressViews.map { it.isSelected }.contains(true)) {
                        addressViews[position].isSelected = !addressViews[position].isSelected
                        isPressed = addressViews[position].isSelected
                        notifyItemChanged(position)
                    } else if (addressViews[position].isSelected) {
                        addressViews[position].isSelected = !addressViews[position].isSelected
                        isPressed = addressViews[position].isSelected
                        notifyItemChanged(position)
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AddressItemBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addressViews.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(position)
    }
}