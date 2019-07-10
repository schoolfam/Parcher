package com.schoolfam.parcher.data.assessment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.user.User
import java.io.Serializable

@Entity(tableName = "assessments"
//    ,
//    foreignKeys = [ForeignKey(entity = Assessment::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("id")),
////        ForeignKey(entity = User::class,
////        parentColumns = arrayOf("id"),
////        childColumns = arrayOf("student_id")),
//        ForeignKey(entity = Subject::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("subject_id"))]
)
data class Assessment(
                 @ColumnInfo(name = "subject_id") val subject_id: Long,
                 @ColumnInfo(name = "student_id") val student_id: Long,
                 @ColumnInfo(name = "assessment_type_id") val assessment_type_id: Long,
                 @ColumnInfo(name = "score") var score: Double
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null
}