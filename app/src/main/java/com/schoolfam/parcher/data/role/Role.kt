package com.schoolfam.parcher.data.role

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "roles")
data class Role( @PrimaryKey @ColumnInfo(name = "id") val id: Long,
            @ColumnInfo(name = "name") val name: String
): Serializable