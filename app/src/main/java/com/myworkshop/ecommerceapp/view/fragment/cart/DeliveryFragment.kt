package com.myworkshop.ecommerceapp.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.myworkshop.ecommerceapp.databinding.FragmentDeliveryBinding
import com.myworkshop.ecommerceapp.model.local.entity.po.AddressView
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.preferences.SharedPref
import com.myworkshop.ecommerceapp.model.remote.dto.address.Address
import com.myworkshop.ecommerceapp.model.remote.dto.address.GetAddressesResult
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.presenter.GetAddressPresenter
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.view.adapter.AddressAdapter
import com.myworkshop.ecommerceapp.view.dialog.AddAddressDialog
import com.myworkshop.ecommerceapp.view.dialog.RefreshDeliveryListCallback
import com.myworkshop.ecommerceapp.view.fragment.checkout.UpdateCheckoutInfo
import java.lang.IllegalStateException

class DeliveryFragment(val updateCheckoutInfo: UpdateCheckoutInfo) : Fragment(), MVPInterfaces.GetAddresses.View, RefreshDeliveryListCallback {
    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var getAddressPresenter: GetAddressPresenter
    private lateinit var userId: String
    private lateinit var addresses: List<AddressView>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliveryBinding.inflate(layoutInflater, container, false)
        val volleyHandler = VolleyHandler(requireContext())
        getAddressPresenter = GetAddressPresenter(volleyHandler, this)
        userId = SharedPref.getSecuredSharedPreferences(requireContext())
            .getString("user_id", "").toString()
        if (userId.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "fetch deliver address list failed: unknown user",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            getAddressPresenter.getAddresses(userId)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (userId.isEmpty()) {
//            Toast.makeText(
//                requireContext(),
//                "fetch deliver address list failed: unknown user",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            getAddressPresenter.getAddresses(userId)
//        }

        binding.apply {
            btnNext.setOnClickListener {
                val checkoutViewPager2 = findViewPagerParent(view)
                if (checkoutViewPager2 != null) {
                    val currentItem = checkoutViewPager2.currentItem
                    val nextPage = currentItem + 1

                    if (checkoutViewPager2.adapter != null && nextPage < checkoutViewPager2.adapter!!.itemCount) {
                        checkoutViewPager2.setCurrentItem(nextPage, true)
                    }
                }
                if (addresses.any { it.isSelected }) {
                    updateCheckoutInfo.updateDeliverAddress(addresses.filter { it.isSelected }[0])
                }
            }
            btnAddAddress.setOnClickListener {
                val dialog = AddAddressDialog(requireContext(), this@DeliveryFragment)
                dialog.show()
            }
        }
    }

    override fun fetchSuccess(getAddressesResult: GetAddressesResult) {
        binding.rvAddresses.apply {
            try{
                layoutManager = LinearLayoutManager(requireContext())
                addresses = buildAddressView(getAddressesResult.addresses)
                adapter = AddressAdapter(addresses, requireContext(),updateCheckoutInfo)
            }catch (e:IllegalStateException){
                Toast.makeText(context,"please check your cart items first!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), "request to fetch deliver address list failed!")
    }

    private fun buildAddressView(addresses: List<Address>): List<AddressView> {
        return addresses.map {
            AddressView(
                type = it.title,
                address = it.address,
                userId = "",
                id = null
            )
        }
    }

    override fun refresh(result: String) {
        getAddressPresenter.getAddresses(userId)
    }
}

fun findViewPagerParent(view: View): ViewPager2? {
    var parentView: ViewParent? = view.parent

    while (parentView != null) {
        if (parentView is ViewPager2) {
            return parentView
        }
        parentView = parentView.parent
    }
    return null
}