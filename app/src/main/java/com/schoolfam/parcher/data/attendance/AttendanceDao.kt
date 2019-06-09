package com.schoolfam.parcher.data.attendance

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface AttendanceDao {

    @Query("SELECT * FROM attendances WHERE id = :attendanceId LIMIT 1")
    fun getAttendanceById(attendanceId:Long): LiveData<Attendance>

    @Query("SELECT * FROM attendances WHERE date = :date AND section_id = :sectionId")
    fun getAttendanceByDateAndSection(date:Long,sectionId:Long): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendances WHERE date = :date AND student_id = :studentId LIMIT 1")
    fun getAttendanceByDateAndStudent(date:Long,studentId:Long): LiveData<Attendance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendance(attendance: Attendance):Long

    @Update
    fun updateAttendance(attendance: Attendance):Int

    @Delete
    fun deleteAttendance(attendance: Attendance):Int

    @Query("SELECT * FROM attendances ORDER BY id")
    fun getAllAttendances(): LiveData<List<Attendance>>

}