package com.schoolfam.parcher.data.attendance

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AttendanceDao {

    @Query("SELECT * FROM attendances WHERE id = :attendanceId LIMIT 1")
    fun getAttendanceById(attendanceId:String): LiveData<Attendance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendance(attendance: Attendance):Long

    @Update
    fun updateAttendance(attendance: Attendance):Int

    @Delete
    fun deleteAttendance(attendance: Attendance):Int

    @Query("SELECT * FROM attendances ORDER BY id")
    fun getAllAttendances(attendance: Attendance): LiveData<List<Attendance>>

}