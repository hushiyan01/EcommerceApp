package com.myworkshop.ecommerceapp.model.remote.util

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import androidx.annotation.DrawableRes
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView

object VolleyImageCaching {
     lateinit var imageLoader: ImageLoader

    fun initialize(context: Context){
        RequestQueue(
            DiskBasedCache(context.cacheDir, 100000),
            BasicNetwork(HurlStack())
        ).apply {
            start()
            imageLoader = ImageLoader(
                this,
                object: ImageLoader.ImageCache{
                    private val internal_cache = LruCache<String, Bitmap>(10)
                    override fun getBitmap(url: String?): Bitmap? {
                        return internal_cache[url]
                    }

                    override fun putBitmap(url: String?, bitmap: Bitmap?) {
                        internal_cache.put(url,bitmap)
                    }
                }
            )
        }
    }

    fun fetchImageUsingVolley(
        url:String,
        imageVolleyImageView: NetworkImageView,
        @DrawableRes placeHolder:Int,
        @DrawableRes errorDrawable:Int
    ){
        imageLoader.get(
            VolleyHandler.FETCH_IMAGE_URL+url,
            ImageLoader.getImageListener(
                imageVolleyImageView,
                placeHolder,
                errorDrawable
            )
        )
        imageVolleyImageView.setImageUrl(url,imageLoader)
    }
}