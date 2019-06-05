package com.schoolfam.parcher.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
           @ColumnInfo(name = "fname") val fname: String,
           @ColumnInfo(name = "lname") val lname: String,
           @ColumnInfo(name = "userName") val username: String,
           @ColumnInfo(name = "password") val password: String,
           @ColumnInfo(name = "emailAddress") val emailAddress: String,
           @ColumnInfo(name = "roleId") val roleId: Long,
           @ColumnInfo(name = "gender") val gender: String
)