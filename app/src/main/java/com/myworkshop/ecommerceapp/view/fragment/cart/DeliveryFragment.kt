package com.myworkshop.ecommerceapp.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.myworkshop.ecommerceapp.databinding.FragmentDeliveryBinding
import com.myworkshop.ecommerceapp.model.local.dao.AddressDao
import com.myworkshop.ecommerceapp.model.local.entity.db.ShoppingDBHelper
import com.myworkshop.ecommerceapp.view.adapter.AddressAdapter
import com.myworkshop.ecommerceapp.view.dialog.AddAddressDialog

class DeliveryFragment : Fragment() {
    private lateinit var binding: FragmentDeliveryBinding
    private lateinit var addressDao: AddressDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliveryBinding.inflate(layoutInflater, container, false)
        addressDao = AddressDao(ShoppingDBHelper(requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvAddresses.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = AddressAdapter(addressDao.getAllAddress())
            }
            btnNext.setOnClickListener {
                val checkoutViewPager2 = findViewPagerParent(view)
                if (checkoutViewPager2 != null) {
                    val currentItem = checkoutViewPager2.currentItem
                    val nextPage = currentItem + 1

                    if (checkoutViewPager2.adapter != null && nextPage < checkoutViewPager2.adapter!!.itemCount) {
                        checkoutViewPager2.setCurrentItem(nextPage, true)
                    }
                }
            }
            btnAddAddress.setOnClickListener {
                val dialog = AddAddressDialog(requireContext())
                dialog.show()
            }
        }
    }

    private fun findViewPagerParent(view: View): ViewPager2? {
        var parentView: ViewParent? = view.parent

        while (parentView != null) {
            if (parentView is ViewPager2) {
                return parentView
            }
            parentView = parentView.parent
        }

        return null
    }

}