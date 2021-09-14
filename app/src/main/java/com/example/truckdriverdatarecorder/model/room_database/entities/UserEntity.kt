package com.example.truckdriverdatarecorder.model.room_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_record")
class UserEntity(
    @ColumnInfo(name = "name") val name: String,
    val email: String,
    val password: String,

    val confirmPassword: String,

//    @ColumnInfo(name = "driverName") val driverName: String,
//    val driverName: String,
//    val phoneNum: String,
//    val address:String,
//    val licenceNum: String,
//    val truckNum: String,
) {

    @PrimaryKey(autoGenerate = true)  var id: Int = 0

}