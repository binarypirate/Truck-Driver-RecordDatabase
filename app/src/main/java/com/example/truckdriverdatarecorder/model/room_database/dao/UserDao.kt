package com.example.truckdriverdatarecorder.model.room_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM driver_record WHERE email=:email")
    fun getUserWithEmail(email: String): LiveData<UserEntity>

    @Query("SELECT * FROM driver_record WHERE email=:email or name=:name")
    fun getUserWithEmailOrUserName(email: String, name: String): UserEntity

    @Query("SELECT * FROM driver_record WHERE id=:id")
    fun getUserWithId(id: Int): LiveData<UserEntity>

    @Query("SELECT * FROM driver_record WHERE driverName=:driverName")
    fun getUser(driverName: String) : UserEntity?

    @Query("SELECT * FROM driver_record")
    fun getUsers(): LiveData<List<UserEntity>>
}