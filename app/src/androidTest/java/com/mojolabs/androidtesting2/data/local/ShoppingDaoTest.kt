package com.mojolabs.androidtesting2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mojolabs.androidtesting2.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase

    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun init() {
        database =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ShoppingItemDatabase::class.java
            ).allowMainThreadQueries().build()

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
