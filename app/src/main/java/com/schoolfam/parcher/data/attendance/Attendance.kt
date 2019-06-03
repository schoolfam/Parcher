package com.schoolfam.parcher.data.attendance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendances")
class Attendance(@PrimaryKey val id: Int) {
}