package com.mojolabs.androidtesting2.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * quantity) FROM shopping_item")
    fun observeTotalPrice(): LiveData<Float>
}
