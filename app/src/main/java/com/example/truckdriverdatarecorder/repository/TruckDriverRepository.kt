package com.example.truckdriverdatarecorder.repository

import androidx.lifecycle.LiveData
import com.example.truckdriverdatarecorder.model.room_database.dao.UserDao
import com.example.truckdriverdatarecorder.model.room_database.entities.UserEntity
import javax.inject.Inject

class TruckDriverRepository @Inject constructor(private val userDao: UserDao){

    fun createNewUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun getUserWithEmail(email: String): LiveData<UserEntity> {
        return userDao.getUserWithEmail(email)
    }

    fun getUserWithEmailOrUserName(email: String, name: String): UserEntity {
        return userDao.getUserWithEmailOrUserName(email, name)
    }

    fun getUserWithId(id: Int): LiveData<UserEntity> {
        return userDao.getUserWithId(id)
    }

    fun craeteNewData(user: UserEntity){
        userDao.insertUser(user)
    }
    fun getUserDataList(): LiveData<List<UserEntity>> {
        return userDao.getUsers()
    }
}