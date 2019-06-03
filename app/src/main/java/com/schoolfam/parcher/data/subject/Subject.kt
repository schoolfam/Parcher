package com.schoolfam.parcher.data.subject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
              @ColumnInfo(name = "name") val subjectName: String
)