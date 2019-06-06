package com.schoolfam.parcher.data.section

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "sections")
data class Section(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
              @ColumnInfo(name = "name") val sectionName: String
): Serializable
{
    override fun toString(): String {
        return sectionName
    }
}