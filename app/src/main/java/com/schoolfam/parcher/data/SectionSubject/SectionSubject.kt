package com.schoolfam.parcher.data.SectionSubject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.subject.Subject
import java.io.Serializable


@Entity(
    tableName = "section_subject",
    foreignKeys =[
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"]),
        ForeignKey(
            entity = Section::class,
            parentColumns = ["id"],
            childColumns = ["section_id"]
            )]
)
data class SectionSubject(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "subject_id") val subjectId: Long,
    @ColumnInfo(name = "section_id") val sectionId: Long
): Serializable