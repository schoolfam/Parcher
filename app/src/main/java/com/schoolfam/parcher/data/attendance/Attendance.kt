package com.schoolfam.parcher.data.attendance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.schoolfam.parcher.utility.TypeConverter
import java.util.*

@Entity(tableName = "attendances")
@TypeConverters(TypeConverter::class)
data class Attendance(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
                      @ColumnInfo(name = "student_id") val studentId: Long,
                      @ColumnInfo(name = "status") val status: Boolean,
                      @ColumnInfo(name = "date") val date: Date
)