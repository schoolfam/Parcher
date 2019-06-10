package com.schoolfam.parcher.data.user

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
           @ColumnInfo(name = "fname") var fname: String,
           @ColumnInfo(name = "lname") var lname: String,
           @ColumnInfo(name = "userName") var username: String,
           @ColumnInfo(name = "emailAddress") var emailAddress: String,
           @ColumnInfo(name = "password") var password: String,
           @ColumnInfo(name = "roleId") val roleId: Long,
           @ColumnInfo(name = "gender") var gender: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id:Long? = null

    override fun toString(): String {
        return "$fname $lname"
    }
}