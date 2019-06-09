package com.schoolfam.parcher.data.attendance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.schoolfam.parcher.utility.TypeConverter
import java.io.Serializable
import java.util.*

@Entity(tableName = "attendances")
@TypeConverters(TypeConverter::class)
data class Attendance(
                      @ColumnInfo(name = "student_id") val studentId: Long,
                      @ColumnInfo(name = "status") val status: Boolean,
                      @ColumnInfo(name = "section_id") val sectionId:Long,
                      @ColumnInfo(name = "date") val date: Date
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null
}