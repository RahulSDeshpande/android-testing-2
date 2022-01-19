package com.mojolabs.androidtesting2.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mojolabs.androidtesting2.MainCoroutineRule
import com.mojolabs.androidtesting2.getOrAwaitValueTest
import com.mojolabs.androidtesting2.repo.FakeShoppingRepo
import com.mojolabs.androidtesting2.util.ApiStatus
import com.mojolabs.androidtesting2.util.MAX_NAME_LENGTH
import com.mojolabs.androidtesting2.util.MAX_PRICE_LENGTH
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var shoppingViewModel: ShoppingViewModel

    @Before
    fun init() {
        shoppingViewModel = ShoppingViewModel(shoppingRepo = FakeShoppingRepo())
    }

    @Test
    fun `insert ShoppingItem with empty field, returns error`() {
        shoppingViewModel.insertShoppingItem(
            name = "Milk",
            quantity = 0,
            price = 10f
        )

        val value = shoppingViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        Truth
            .assertThat(value.getContentIfNotHandled()?.status)
            .isEqualTo(ApiStatus.ERROR)
    }

    @Test
    fun `insert ShoppingItem with too long name, returns error`() {
        val name =
            buildString {
                for (i in 1..MAX_NAME_LENGTH + 1) {
                    append("X")
                }
            }

        shoppingViewModel.insertShoppingItem(
            name = name,
            quantity = 2,
            price = 10f
        )

        val value = shoppingViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        Truth
            .assertThat(value.getContentIfNotHandled()?.status)
            .isEqualTo(ApiStatus.ERROR)
    }

    @Test
    fun `insert ShoppingItem with too long price, returns error`() {
        val price =
            buildString {
                for (i in 1..MAX_PRICE_LENGTH + 1) {
                    append("1")
                }
            }

        shoppingViewModel.insertShoppingItem(
            name = "Milk",
            quantity = 2,
            price = price.toFloat()
        )

        val value = shoppingViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        Truth
            .assertThat(value.getContentIfNotHandled()?.status)
            .isEqualTo(ApiStatus.ERROR)
    }

    @Test
    fun `insert ShoppingItem with too high quantity, returns error`() {
        shoppingViewModel.insertShoppingItem(
            name = "Milk",
            quantity = 2,
            price = 20000f
        )

        val value = shoppingViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        Truth
            .assertThat(value.getContentIfNotHandled()?.status)
            .isEqualTo(ApiStatus.ERROR)
    }

    @Test
    fun `insert ShoppingItem with valid input, returns error`() {
        shoppingViewModel.insertShoppingItem(
            name = "Milk",
            quantity = 2,
            price = 20f
        )

        val value = shoppingViewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        Truth
            .assertThat(value.getContentIfNotHandled()?.status)
            .isEqualTo(ApiStatus.SUCCESS)
    }
}
