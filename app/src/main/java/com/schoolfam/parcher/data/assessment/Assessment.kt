package com.schoolfam.parcher.data.assessment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assessments")
class Assessment(@PrimaryKey val id: Int) {
}