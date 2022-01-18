package com.mojolabs.androidtesting2.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mojolabs.androidtesting2.data.local.ShoppingItem
import com.mojolabs.androidtesting2.data.remote.responses.ImageResponse
import com.mojolabs.androidtesting2.util.ApiResource

class FakeShoppingRepo : ShoppingRepo {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchImage(imageQuery: String): ApiResource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            ApiResource.error(msg = "Network error.")
        } else {
            ApiResource.success(
                ImageResponse(
                    hits = listOf(),
                    total = 0,
                    totalHits = 0
                )
            )
        }
    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }
}
