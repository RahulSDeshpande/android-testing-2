package com.mojolabs.androidtesting2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.mojolabs.androidtesting2.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("db_shopping")
    lateinit var database: ShoppingItemDatabase

    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun init() {
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun deInit() {
        database.close()
    }

    @Test
    fun insertShoppingItem() =
        runBlockingTest {

            val shoppingItem =
                ShoppingItem(
                    id = 1,
                    name = "Milk",
                    quantity = 2,
                    price = 20f,
                    imageUrl = "url"
                )

            shoppingDao.insertShoppingItem(shoppingItem)

            val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

            Truth.assertThat(allShoppingItems).contains(shoppingItem)
        }

    @Test
    fun deleteShoppingItem() =
        runBlockingTest {

            val shoppingItem =
                ShoppingItem(
                    id = 1,
                    name = "Milk",
                    quantity = 2,
                    price = 20f,
                    imageUrl = "url"
                )

            shoppingDao.insertShoppingItem(shoppingItem)

            shoppingDao.deleteShoppingItem(shoppingItem)

            val allShoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

            Truth.assertThat(allShoppingItems).doesNotContain(shoppingItem)
        }

    @Test
    fun observeTotalPriceSum() =
        runBlockingTest {

            val shoppingItem1 =
                ShoppingItem(
                    id = 1,
                    name = "Milk1",
                    quantity = 1,
                    price = 20f,
                    imageUrl = "url"
                )

            val shoppingItem2 =
                ShoppingItem(
                    id = 2,
                    name = "Milk2",
                    quantity = 2,
                    price = 20f,
                    imageUrl = "url"
                )

            val shoppingItem3 =
                ShoppingItem(
                    id = 3,
                    name = "Milk3",
                    quantity = 3,
                    price = 20f,
                    imageUrl = "url"
                )

            shoppingDao.insertShoppingItem(shoppingItem1)
            shoppingDao.insertShoppingItem(shoppingItem2)
            shoppingDao.insertShoppingItem(shoppingItem3)

            val totalPriceSum = shoppingDao.observeTotalPrice().getOrAwaitValue()

            Truth.assertThat(totalPriceSum).isEqualTo((20 * 1) + (20 * 2) + (20 * 3))
        }
}
