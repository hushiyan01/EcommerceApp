package com.myworkshop.ecommerceapp.view.fragment.main

import androidx.fragment.app.Fragment

interface OnGoToSubCategoryViewPagerCallBack {
    fun go(subCategoryId:String, categoryTitle:String)
}

interface OnGoToProductDetailCallBack{
    fun go(productId:String)
}

interface OnChangeToolbarCallback{
    fun changeToolbar(fragment:Fragment, toolBarTitle:String)
}

