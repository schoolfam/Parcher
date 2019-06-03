package com.schoolfam.parcher.data.assessmentType

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assessmentTypes")
class AssessmentType(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                     @ColumnInfo(name = "name") val assessment_name: String,
                     @ColumnInfo(name = "maximum_point") val maximumPoint: Double
)