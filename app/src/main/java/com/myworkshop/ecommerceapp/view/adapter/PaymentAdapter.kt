package com.myworkshop.ecommerceapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.PaymentItemBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.PaymentView
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo

class PaymentAdapter(val paymentMethods: List<PaymentView>, val context: Context,val updateCheckoutInfo: UpdateCheckoutInfo) :
    RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {
    private lateinit var binding: PaymentItemBinding
    private var isSelected = false

    inner class PaymentViewHolder(binding: PaymentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val payment = binding.tvPayment
        private val button = binding.btnPaymentSelector
        var isCurSelected = false

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            payment.text = paymentMethods[position].payment
            button.setOnClickListener {
                if (!isSelected) {
                    isSelected = true
                    isCurSelected = true
                    paymentMethods[position].isSelected = true
                    it.setBackgroundResource(R.drawable.button_selected)
                    updateCheckoutInfo.updatePaymentMethod(paymentMethods[position].payment)
                    notifyDataSetChanged()
                } else if (isCurSelected) {
                    isSelected = false
                    isCurSelected = false
                    paymentMethods[position].isSelected = false
                    it.setBackgroundResource(R.drawable.button_unselected)
                    notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "you can only select one payment method!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = PaymentItemBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding)
    }

    override fun getItemCount(): Int = paymentMethods.size

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(position)
    }
}