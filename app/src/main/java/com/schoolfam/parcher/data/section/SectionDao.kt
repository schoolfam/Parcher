package com.schoolfam.parcher.data.section

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SectionDao {

    @Query("SELECT * FROM sections WHERE id = :sectionId LIMIT 1")
    fun getSectionById(sectionId:String): LiveData<Section>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSection(section: Section):Long

    @Update
    fun updateSection(section: Section):Int

    @Delete
    fun deleteSection(section: Section):Int

    @Query("SELECT * FROM sections ORDER BY id")
    fun getAllSections(section: Section): LiveData<List<Section>>

}