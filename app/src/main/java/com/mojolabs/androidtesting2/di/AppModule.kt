package com.mojolabs.androidtesting2.di

import android.content.Context
import androidx.room.Room
import com.mojolabs.androidtesting2.data.local.ShoppingDao
import com.mojolabs.androidtesting2.data.local.ShoppingItemDatabase
import com.mojolabs.androidtesting2.data.remote.PixabayApi
import com.mojolabs.androidtesting2.repo.ShoppingRepoImpl
import com.mojolabs.androidtesting2.util.API_BASE_URL
import com.mojolabs.androidtesting2.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ShoppingItemDatabase::class.java,
        DATABASE_NAME
    )

    @Singleton
    @Provides
    fun provideShoppingRepo(
        shoppingDao: ShoppingDao,
        pixabayApi: PixabayApi
    ) = ShoppingRepoImpl(
        shoppingDao = shoppingDao,
        pixabayApi = pixabayApi
    )

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi() =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .build()
            .create(PixabayApi::class.java)
}
