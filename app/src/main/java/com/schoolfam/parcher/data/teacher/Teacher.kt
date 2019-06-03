package com.schoolfam.parcher.data.teacher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
data class Teacher(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
              @ColumnInfo(name = "user_id") val userId: Int
)