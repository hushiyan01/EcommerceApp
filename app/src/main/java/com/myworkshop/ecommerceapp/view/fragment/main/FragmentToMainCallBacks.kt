package com.myworkshop.ecommerceapp.view.fragment.main

import androidx.fragment.app.Fragment
import com.myworkshop.ecommerceapp.model.remote.dto.product.Product

interface OnGoToSubCategoryViewPagerCallBack {
    fun goToSubCategoryFragment(subCategoryId: String, categoryTitle: String)
}

interface OnChangeToolbarCallback {
    fun changeToolbar(fragment: Fragment, toolBarTitle: String)
}

