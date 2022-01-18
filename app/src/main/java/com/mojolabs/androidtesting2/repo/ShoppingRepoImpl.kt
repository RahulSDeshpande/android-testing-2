package com.mojolabs.androidtesting2.repo

import androidx.lifecycle.LiveData
import com.mojolabs.androidtesting2.data.local.ShoppingDao
import com.mojolabs.androidtesting2.data.local.ShoppingItem
import com.mojolabs.androidtesting2.data.remote.PixabayApi
import com.mojolabs.androidtesting2.util.ApiResource
import javax.inject.Inject

class ShoppingRepoImpl
@Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayApi: PixabayApi
) : ShoppingRepo {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchImage(imageQuery: String) =
        try {
            val apiResponse = pixabayApi.searchImage(imageQuery)

            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let {
                    return@let ApiResource.success(it)
                } ?: ApiResource.error("Error 1")
            } else {
                ApiResource.error("Error 2")
            }
        } catch (exception: Exception) {
            ApiResource.error(msg = "Error 2")
        }
}
