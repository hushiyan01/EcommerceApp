package com.myworkshop.ecommerceapp.model.local.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object UIUtils {
    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}