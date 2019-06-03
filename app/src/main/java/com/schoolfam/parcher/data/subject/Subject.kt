package com.schoolfam.parcher.data.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(@PrimaryKey val id: Int) {
}