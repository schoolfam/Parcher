package com.schoolfam.parcher.data.parent

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parents")
class Parent(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
             @ColumnInfo(name = "user_id") val userId: Int
)