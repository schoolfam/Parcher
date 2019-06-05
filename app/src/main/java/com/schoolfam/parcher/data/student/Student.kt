package com.schoolfam.parcher.data.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.user.User

@Entity(tableName = "students",
    foreignKeys = [ForeignKey(entity = Parent::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parent_id")
    ), ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"))]
)
data class Student(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "parent_id") val parentId: Long,
    @ColumnInfo(name = "section_id") val sectionId:Long

)