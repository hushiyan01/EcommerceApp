package com.myworkshop.ecommerceapp.view.fragment.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.FragmentOrdersBinding
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.order.GetOrdersResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.GetOrdersPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.OrdersAdapter

class OrdersFragment : Fragment(),MVPInterfaces.GetOrders.View {
    private lateinit var binding:FragmentOrdersBinding
    private lateinit var presenter: GetOrdersPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        presenter = GetOrdersPresenter(VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = SharedPref.getSecuredSharedPreferences(requireContext()).getString("user_id","unknown_user")?:"unknown_user"
        presenter.getOrders(userId)
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).changeToolbar(this,"Orders")
    }

    override fun getOrdersSuccess(getOrdersResult: GetOrdersResult) {
        binding.rvOrders.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OrdersAdapter(getOrdersResult.orders, requireActivity().supportFragmentManager)
        }
    }

    override fun getOrdersFailed(errorMsg: String) {
        Toast.makeText(requireContext(),"fetching orders data failed",Toast.LENGTH_SHORT).show()
    }
}