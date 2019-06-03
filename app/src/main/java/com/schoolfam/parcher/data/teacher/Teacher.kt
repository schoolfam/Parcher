package com.schoolfam.parcher.data.teacher

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
class Teacher(@PrimaryKey val id: Int) {
}