package com.myworkshop.ecommerceapp.view.fragment.main

import androidx.fragment.app.Fragment

interface OnGoToSubCategoryViewPagerCallBack {
    fun goToSubCategoryFragment(subCategoryId:String, categoryTitle:String)
}

interface OnGoToProductDetailCallBack{
    fun goToProductDetailFragment(productId:String)
}

interface OnChangeToolbarCallback{
    fun changeToolbar(fragment:Fragment, toolBarTitle:String)
}

