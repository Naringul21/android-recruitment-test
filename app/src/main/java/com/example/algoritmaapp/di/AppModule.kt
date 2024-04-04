package com.example.algoritmaapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.algoritmaapp.data.local.AppDatabase
import com.example.algoritmaapp.data.local.DataDao
import com.example.algoritmaapp.data.repository.DataRepository
import com.example.algoritmaapp.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSocketDao(database: AppDatabase): DataDao {
        return database.dataDao()
    }

    @Provides
    @Singleton
    fun provideDataRepository(dataDao: DataDao): DataRepository {
        return DataRepository(dataDao)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideMainViewModel(
        dataRepository: DataRepository
    ): MainViewModel {
        return MainViewModel(dataRepository)
    }

}