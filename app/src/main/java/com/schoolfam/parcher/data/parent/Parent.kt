package com.schoolfam.parcher.data.parent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parents")
class Parent(@PrimaryKey val id: Int) {
}