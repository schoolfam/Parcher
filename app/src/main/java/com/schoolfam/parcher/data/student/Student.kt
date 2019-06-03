package com.schoolfam.parcher.data.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
              @ColumnInfo(name = "user_id") val userId: Int
)