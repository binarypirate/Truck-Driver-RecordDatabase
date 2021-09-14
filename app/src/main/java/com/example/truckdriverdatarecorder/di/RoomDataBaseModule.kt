package com.example.truckdriverdatarecorder.di

import android.app.Application
import androidx.room.Room
import com.example.truckdriverdatarecorder.model.room_database.dao.UserDao
import com.example.truckdriverdatarecorder.model.room_database.db.TruckDriverDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataBaseModule {

    @Provides
    @Singleton
    fun provideElectroDB(application: Application): TruckDriverDB {
        return Room.databaseBuilder(application, TruckDriverDB::class.java, "truck_driver_db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(truckDriverDB: TruckDriverDB): UserDao {
        return truckDriverDB.userDao()
    }
}