package com.schoolfam.parcher.data.subject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "subjects")
data class Subject(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
              @ColumnInfo(name = "name") val subjectName: String
): Serializable