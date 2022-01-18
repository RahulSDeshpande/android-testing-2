package com.mojolabs.androidtesting2.repo

import androidx.lifecycle.LiveData
import com.mojolabs.androidtesting2.data.local.ShoppingItem
import com.mojolabs.androidtesting2.data.remote.responses.ImageResponse
import com.mojolabs.androidtesting2.util.Resource

interface ShoppingRepo {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}
