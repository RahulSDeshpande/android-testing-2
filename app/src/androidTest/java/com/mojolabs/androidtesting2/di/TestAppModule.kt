package com.mojolabs.androidtesting2.di

import android.content.Context
import androidx.room.Room
import com.mojolabs.androidtesting2.data.local.ShoppingItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class TestAppModule {

    @Singleton
    @Provides
    @Named("db_shopping")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, ShoppingItemDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}
