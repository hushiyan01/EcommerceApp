package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.OrderItemBinding
import com.myworkshop.ecommerceapp.model.remote.dto.order.Order
import com.myworkshop.ecommerceapp.view.fragment.orders.OrderDetailFragment

class OrdersAdapter(private val orders: List<Order>,private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {
    private lateinit var binding: OrderItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = OrderItemBinding.inflate(inflater,parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int = orders.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener{
            val orderDetailFragment = OrderDetailFragment()
            val bundle = Bundle()
            bundle.putString("order_id", orders[position].order_id)
            orderDetailFragment.arguments = bundle
            fragmentManager.beginTransaction().replace(R.id.fg_home_container, orderDetailFragment).addToBackStack("order_detail_fragment").commit()
        }
    }

    inner class OrderViewHolder(val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val orderId = binding.tvOrderId
        private val billAmount = binding.tvBillAmount
        private val status = binding.tvOrderStatusOrders
        private val addressTitle = binding.tvAddressTitle
        private val address = binding.tvAddressContent
        private val payment = binding.tvPaymentMethodOrders
        private val time = binding.tvOrderTime

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            orderId.text = "order id: ${orders[position].order_id}"
            billAmount.text = "$ ${orders[position].bill_amount}"
            status.text = "order status: ${orders[position].order_status}"
            addressTitle.text = orders[position].address_title
            address.text = orders[position].address
            payment.text = "payment method: ${orders[position].payment_method}"
            time.text = "order placed at: ${orders[position].order_date}"
        }
    }


}