package com.example.truckdriverdatarecorder.model.room_database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.truckdriverdatarecorder.model.room_database.dao.UserDao
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class TruckDriverDB: RoomDatabase() {
    abstract fun userDao(): UserDao

}