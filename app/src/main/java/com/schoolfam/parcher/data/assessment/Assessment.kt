package com.schoolfam.parcher.data.assessment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.subject.Subject

@Entity(tableName = "assessments",
    foreignKeys = [ForeignKey(entity = Assessment::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id")), ForeignKey(entity = Student::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("student_id")), ForeignKey(entity = Subject::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("subject_id"))]
)
data class Assessment(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
                 @ColumnInfo(name = "subject_id") val subjectId: Long,
                 @ColumnInfo(name = "student_id") val studentId: Long,
                 @ColumnInfo(name = "assessment_type_id") val assessmentTypeId: Long,
                 @ColumnInfo(name = "score") val score: Double
)