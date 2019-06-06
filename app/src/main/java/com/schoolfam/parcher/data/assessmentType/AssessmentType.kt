package com.schoolfam.parcher.data.assessmentType

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "assessmentTypes")
data class AssessmentType(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
                     @ColumnInfo(name = "name") val assessment_name: String,
                     @ColumnInfo(name = "maximum_point") val maximumPoint: Double
): Serializable