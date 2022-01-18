package com.mojolabs.androidtesting2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mojolabs.androidtesting2.data.local.ShoppingItem
import com.mojolabs.androidtesting2.data.remote.responses.ImageResponse
import com.mojolabs.androidtesting2.repo.ShoppingRepo
import com.mojolabs.androidtesting2.util.ApiResource
import com.mojolabs.androidtesting2.util.Event
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val shoppingRepo: ShoppingRepo
) : ViewModel() {

    val shoppingItems = shoppingRepo.observeAllShoppingItems()

    val totalPrice = shoppingRepo.observeTotalPrice()

    private val _images = MutableLiveData<Event<ApiResource<ImageResponse>>>()
    val images: LiveData<Event<ApiResource<ImageResponse>>> = _images

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> = _currentImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<ApiResource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<ApiResource<ShoppingItem>>> =
        _insertShoppingItemStatus

    fun setCurrentImageUrl(url: String) {
        _currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) =
        viewModelScope.launch {
            shoppingRepo.deleteShoppingItem(shoppingItem)
        }

    fun insertShoppingItem(shoppingItem: ShoppingItem) =
        viewModelScope.launch {
            shoppingRepo.insertShoppingItem(shoppingItem)
        }

    fun insertShoppingItem(
        name: String,
        quantity: Int,
        price: String
    ) {
    }

    fun searchImage(imageQuery: String) {}
}
