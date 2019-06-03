package com.schoolfam.parcher.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(@PrimaryKey val id: Int) {
}