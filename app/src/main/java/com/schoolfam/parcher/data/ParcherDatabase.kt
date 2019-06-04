package com.schoolfam.parcher.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.admin.AdminDao
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.assessment.AssessmentDao
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.assessmentType.AssessmentTypeDao
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.attendance.AttendanceDao
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.parent.ParentDao
import com.schoolfam.parcher.data.role.Role
import com.schoolfam.parcher.data.role.RoleDao
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.section.SectionDao
import com.schoolfam.parcher.data.student.StudentDao
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.data.teacher.TeacherDao
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao

@Database(entities = [
    Admin::class,
    Assessment::class,
    AssessmentType::class,
    Attendance::class,
    Parent::class,
    Role::class,
    Section::class,
    Subject::class,
    Teacher::class,
    User::class],version = 1)

abstract class ParcherDatabase : RoomDatabase() {
    
    abstract fun adminDao():AdminDao
    abstract fun assessmentDao(): AssessmentDao
    abstract fun assessmentTypeDao():AssessmentTypeDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun parentDao():ParentDao
    abstract fun roleDao():RoleDao
    abstract fun sectionDao(): SectionDao
    abstract fun studentDao():StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun userDao(): UserDao


    companion object {
        private var INSTANCE: ParcherDatabase? = null

        fun getInstance(context: Context): ParcherDatabase? {
            if (INSTANCE == null) {
                synchronized(ParcherDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ParcherDatabase::class.java, "parcher.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}