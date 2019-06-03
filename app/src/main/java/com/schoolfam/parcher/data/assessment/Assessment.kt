package com.schoolfam.parcher.data.assessment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.subject.Subject

@Entity(tableName = "assessments",
    primaryKeys = arrayOf("assessmentId", "studentId"),
    foreignKeys = arrayOf(
        ForeignKey(entity = Assessment::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("assessmentId")),
        ForeignKey(entity = Student::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("studentId")),
        ForeignKey(entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subjectId"))

    )
)
data class Assessment(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                 @ColumnInfo(name = "subject_id") val subjectId: Int,
                 @ColumnInfo(name = "student_id") val studentId: Int,
                 @ColumnInfo(name = "assessment_type_id") val assessmentTypeId: Int,
                 @ColumnInfo(name = "score") val score: Double
)