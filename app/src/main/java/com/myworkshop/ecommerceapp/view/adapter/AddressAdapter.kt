package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.AddressItemBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.Address
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.merge

class AddressAdapter(
    val addresses: List<Address>,
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    private lateinit var binding: AddressItemBinding

    inner class AddressViewHolder(binding: AddressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val type = binding.tvAddressType
        private val address = binding.tvAddress
        private val selectorButton = binding.btnAddressSelector

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            type.text = addresses[position].type
            address.text = addresses[position].address
            selectorButton.isPressed = addresses[position].isSelected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = AddressItemBinding.inflate(inflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int = addresses.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.findViewById<AppCompatButton>(R.id.btn_address_selector)
            .setOnClickListener {
                if(!addresses.map { it.isSelected }.contains(true)){
                    addresses[position].isSelected = !addresses[position].isSelected
                    notifyItemChanged(position)
                }else if(addresses[position].isSelected){
                    addresses[position].isSelected = !addresses[position].isSelected
                    notifyItemChanged(position)
                }
            }
    }
}