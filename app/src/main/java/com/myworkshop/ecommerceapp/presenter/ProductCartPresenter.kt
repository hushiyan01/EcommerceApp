package com.myworkshop.ecommerceapp.presenter

import com.myworkshop.ecommerceapp.model.local.dao.CartDao
import com.myworkshop.ecommerceapp.model.local.entity.po.CartItem

class ProductCartPresenter(private val cartDao: CartDao, private val view:MVPInterfaces.ProductCart.View):MVPInterfaces.ProductCart.Presenter {
    override fun fetchProductsInCart() {
        view.loadCart(cartDao.getAllItems())
    }

    override fun productMinus1(id:String) {
        cartDao.updateById(id, -1)
    }

    override fun productPlus1(id: String) {
        cartDao.updateById(id, 1)
    }

    override fun insertNewItem(cartItem: CartItem) {
        cartDao.save(cartItem)
    }

    override fun isInCart(id: String):Boolean {
        return cartDao.isInCart(id)
    }

    override fun remove(id: String): Int {
        return cartDao.delete(id)
    }
}