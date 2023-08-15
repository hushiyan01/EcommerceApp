package com.myworkshop.ecommerceapp.view.fragment.main

import androidx.fragment.app.Fragment

interface OnGoToSubCategoryViewPagerCallBack {
    fun go(subCategoryId:String, categoryTitle:String)
}

interface OnChangeToolbarCallback{
    fun changeToolbar(fragment:Fragment, toolBarTitle:String)
}