package com.schoolfam.parcher.data.user

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
           @ColumnInfo(name = "fname") val fname: String,
           @ColumnInfo(name = "lname") val lname: String,
           @ColumnInfo(name = "userName") val username: String,
           @ColumnInfo(name = "emailAddress") val emailAddress: String,
           @ColumnInfo(name = "password") val password: String,
           @ColumnInfo(name = "roleId") val roleId: Long,
           @ColumnInfo(name = "gender") val gender: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id:Long? = null

    override fun toString(): String {
        return "$fname $lname"
    }
}