package com.schoolfam.parcher.data.role

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roles")
data class Role( @PrimaryKey @ColumnInfo(name = "id") val id: Int,
            @ColumnInfo(name = "name") val name: String
)