package com.schoolfam.parcher.data.admin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "admins")
data class Admin(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
            @ColumnInfo(name = "userId") val userId: Int
)