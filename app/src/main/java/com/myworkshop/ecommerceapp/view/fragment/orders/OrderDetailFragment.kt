package com.myworkshop.ecommerceapp.view.fragment.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentOrderDetailBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem
import com.myworkshop.ecommerceapp.model.remote.dto.order.Item
import com.myworkshop.ecommerceapp.model.remote.dto.order.OrderDetailResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.OrderDetailPresenter
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.CartCheckoutAdapter

class OrderDetailFragment : Fragment(), MVPInterfaces.OrderDetail.View {
    private lateinit var binding: FragmentOrderDetailBinding
    private lateinit var presenter: OrderDetailPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        presenter = OrderDetailPresenter(VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orderId = arguments?.getString("order_id") ?: ""
        presenter.getOrderDetail(orderId)
        (requireActivity() as MainActivity).changeToolbar(this, "Order: #$orderId")
        (requireActivity() as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
    }

    override fun getOrderDetailSuccess(orderDetailResult: OrderDetailResult) {
        binding.apply {
            tvOrderId.text = "# ${orderDetailResult.order.order_id}"
            tvOrderStatus.text = orderDetailResult.order.order_status
            val items = orderDetailResult.order.items.map { buildCartItem(it) }
            rvCartItemsConfirmed.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = CartCheckoutAdapter(items)
            }
            tvTotalPrice.text = items.map { it.price * it.num }.sum().toString()
            tvAddressTitle.text = orderDetailResult.order.address_title
            tvAddressContent.text = orderDetailResult.order.address
            tvPaymentOption.text = orderDetailResult.order.payment_method
        }
    }

    override fun getOrderDetailFailed(errorMsg: String) {
        Toast.makeText(requireContext(), "fetch order detail failed!", Toast.LENGTH_SHORT).show()
    }

    private fun buildCartItem(item: Item): CartItem {
        return CartItem(
            null,
            "",
            item.product_name,
            item.unit_price.toFloat(),
            item.product_image_url,
            item.description,
            item.quantity.toInt()
        )
    }

}