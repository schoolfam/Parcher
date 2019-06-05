package com.schoolfam.parcher.data.admin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.user.User

@Entity(tableName = "admins",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE)]
)
data class Admin(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
            @ColumnInfo(name = "user_id") val userId: Long
)