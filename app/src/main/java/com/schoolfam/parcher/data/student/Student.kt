package com.schoolfam.parcher.data.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.parent.Parent

@Entity(tableName = "students",
    foreignKeys = arrayOf(ForeignKey(entity = Parent::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentId"))
    )
)
data class Student(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
              @ColumnInfo(name = "user_id") val userId: Int,
              @ColumnInfo(name = "parent_id") val parentId: Int

)