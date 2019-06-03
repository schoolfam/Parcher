package com.schoolfam.parcher.data.attendance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "attendances")
class Attendance(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                 @ColumnInfo(name = "student_id") val studentId: Int,
                 @ColumnInfo(name = "section_id") val sectionId: Int,
                 @ColumnInfo(name = "date") val date: Date
)