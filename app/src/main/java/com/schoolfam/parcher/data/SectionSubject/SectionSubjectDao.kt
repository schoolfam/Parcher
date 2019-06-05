package com.schoolfam.parcher.data.SectionSubject

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schoolfam.parcher.data.section.Section

@Dao
interface SectionSubjectDao {

    @Query("SELECT * FROM section_subject WHERE section_id = :sectionId LIMIT 1")
    fun getSectionSubjectBySectionId(sectionId:Long): LiveData<SectionSubject>

    @Query("SELECT * FROM section_subject WHERE id = :sectionSubjectId LIMIT 1")
    fun getSectionSubjectById(sectionSubjectId:Long): LiveData<SectionSubject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSectionSubject(sectionSubject: SectionSubject):Long

    @Update
    fun updateSectionSubject(sectionSubject: SectionSubject):Int

    @Delete
    fun deleteSectionSubject(sectionSubject: SectionSubject):Int

    @Query("SELECT * FROM section_subject ORDER BY id")
    fun getAllSectionSubjects(): LiveData<List<SectionSubject>>
}