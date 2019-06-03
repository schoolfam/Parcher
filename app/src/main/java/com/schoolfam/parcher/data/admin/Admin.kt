package com.schoolfam.parcher.data.admin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admins")
class Admin(@PrimaryKey val id: Int) {
}