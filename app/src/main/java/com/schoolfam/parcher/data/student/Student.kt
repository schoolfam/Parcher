package com.schoolfam.parcher.data.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student(@PrimaryKey val id: Int) {
}