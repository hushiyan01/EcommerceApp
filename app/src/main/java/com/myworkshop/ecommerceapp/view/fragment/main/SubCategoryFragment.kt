package com.myworkshop.ecommerceapp.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.myworkshop.ecommerceapp.R
import com.myworkshop.ecommerceapp.databinding.CustomTabBinding
import com.myworkshop.ecommerceapp.databinding.FragmentSubCategoryBinding
import com.myworkshop.ecommerceapp.model.local.util.UIUtils
import com.myworkshop.ecommerceapp.model.remote.dto.category.SubCategoryResult
import com.myworkshop.ecommerceapp.model.remote.dto.category.Subcategory
import com.myworkshop.ecommerceapp.model.remote.util.VolleyHandler
import com.myworkshop.ecommerceapp.model.remote.util.VolleyImageCaching
import com.myworkshop.ecommerceapp.presenter.MVPInterfaces
import com.myworkshop.ecommerceapp.presenter.SubCategoryPresenter
import com.myworkshop.ecommerceapp.view.activity.MainActivity
import com.myworkshop.ecommerceapp.view.adapter.FragmentViewpagerAdapter

class SubCategoryFragment(
    private val id: String,
    private val toolBarTitle: String
) : Fragment(), MVPInterfaces.SubCategory.View {

    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var presenter: SubCategoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        presenter = SubCategoryPresenter(VolleyHandler(requireContext()), this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null && activity is MainActivity) {
            (activity as MainActivity).changeToolbar(this, toolBarTitle)
        }
        presenter.fetchSubCategoriesById(id)
    }

    override fun fetchSuccess(subCategoryResult: SubCategoryResult) {
        binding.apply {
            val subcategories = subCategoryResult.subcategories
            val hashMap = HashMap<Int, Subcategory>()

            var index = 0
            for (i in subcategories) {
                hashMap[index++] = i
            }
            val subcategoryFrags =
                subcategories.map {
                    val bundle = Bundle()
                    bundle.putParcelable("sub_category", it)
                    val subCategoryFragment = SubCategoryItemListFragment()
                    subCategoryFragment.arguments = bundle
                    subCategoryFragment
                }
            vpSubCategory.adapter = FragmentViewpagerAdapter(subcategoryFrags, requireActivity())

            TabLayoutMediator(subCategoryTabLayout, vpSubCategory) {

                    tab, position ->

                val tabBinding = CustomTabBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    subCategoryTabLayout,
                    false
                )
                tabBinding.apply {
                    val imgUrl = hashMap[position]!!.subcategory_image_url
                    VolleyImageCaching.fetchImageUsingVolley(
                        imgUrl,
                        tabImage,
                        R.drawable.baseline_density_medium_24,
                        R.drawable.baseline_density_medium_24
                    )
                    tabText.text = hashMap[position]!!.subcategory_name
                }
                tab.customView = tabBinding.root
            }.attach()
        }
    }

    override fun fetchFailed(errorMsg: String) {
        UIUtils.showSnackBar(requireView(), "load sub categories failed")
    }


}