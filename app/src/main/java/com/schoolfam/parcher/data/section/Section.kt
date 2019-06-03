package com.schoolfam.parcher.data.section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
class Section(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
              @ColumnInfo(name = "name") val sectionName: String
)