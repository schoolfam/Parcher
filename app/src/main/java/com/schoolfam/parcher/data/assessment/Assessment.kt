package com.schoolfam.parcher.data.assessment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assessments")
class Assessment(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                 @ColumnInfo(name = "subject_id") val subjectId: Int,
                 @ColumnInfo(name = "student_id") val studentId: Int,
                 @ColumnInfo(name = "assessment_type_id") val assessmentTypeId: Int,
                 @ColumnInfo(name = "score") val score: Double
)