package com.sngular.flixitApp.di

import android.content.Context
import androidx.room.Room
import com.sngular.flixitApp.data.repository.database.FlixitAppDatabase
import com.sngular.flixitApp.util.Constants.Companion.ROOM_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FlixitAppDatabase::class.java, ROOM_DATABASE)
            .build()


    @Singleton
    @Provides
    fun providePopularMovieDao(db: FlixitAppDatabase) = db.getPopularMovieDao()

    @Singleton
    @Provides
    fun provideRateMovieDao(db: FlixitAppDatabase) = db.getRateMovieDao()

    @Singleton
    @Provides
    fun provideUpcomingMovieDao(db: FlixitAppDatabase) = db.getUpcomingDao()

    @Singleton
    @Provides
    fun provideLocationDao(db: FlixitAppDatabase) = db.getLocationDao()

    @Singleton
    @Provides
    fun providePersonDao(db: FlixitAppDatabase) = db.getPersonDao()
}