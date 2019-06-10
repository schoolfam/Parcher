package com.schoolfam.parcher.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.schoolfam.parcher.data.SectionSubject.SectionSubject
import com.schoolfam.parcher.data.SectionSubject.SectionSubjectDao
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.admin.AdminDao
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.announcement.AnnouncementDao
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
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.student.StudentDao
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.subject.SubjectDao
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.data.teacher.TeacherDao
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao
import com.schoolfam.parcher.utility.TypeConverter
import com.schoolfam.parcher.utility.ioThread

@Database(entities = [
    Admin::class,
    Announcement::class,
    Assessment::class,
    AssessmentType::class,
    Attendance::class,
    Parent::class,
    Role::class,
    Section::class,
    SectionSubject::class,
    Subject::class,
    Student::class,
    Teacher::class,
    User::class],version = 1)
@TypeConverters(TypeConverter::class)
abstract class ParcherDatabase : RoomDatabase() {

    abstract fun adminDao():AdminDao
    abstract fun announcementDao(): AnnouncementDao
    abstract fun assessmentDao(): AssessmentDao
    abstract fun assessmentTypeDao():AssessmentTypeDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun parentDao():ParentDao
    abstract fun roleDao():RoleDao
    abstract fun sectionDao(): SectionDao
    abstract fun studentDao():StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun sectionsubjectDao(): SectionSubjectDao
    abstract fun teacherDao(): TeacherDao
    abstract fun userDao(): UserDao


    companion object {

        @Volatile
        private var INSTANCE: ParcherDatabase? = null

        fun getInstance(context: Context): ParcherDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParcherDatabase::class.java, "parcher_database")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            ioThread{
                                val db = getInstance(context)
                                val userDao = db.userDao()
                                val adminDao = db.adminDao()
                                val sectionDao = db.sectionDao()
                                val subjectDao = db.subjectDao()
                                val sectionSubjectDao = db.sectionsubjectDao()
                                val assessmentTypeDao = db.assessmentTypeDao()
                                val roleDao = db.roleDao()

                                val user = User("Sinkumen","Asefa","Cnku","sinkumen@gmail.com","123456",1,"Male")
                                val admin = Admin(userDao.insertUser(user))
                                adminDao.insertAdmin(admin)
                                print("User Added-------------------------------------------------------------------------------------------")

                                val secion1 = sectionDao.insertSection(Section(1,"Grade 1"))
                                val secion2 = sectionDao.insertSection(Section(2,"Grade 2"))
                                val secion3 = sectionDao.insertSection(Section(3,"Grade 3"))
                                val secion4 = sectionDao.insertSection(Section(4,"Grade 4"))
                                val secion5 = sectionDao.insertSection(Section(5,"Grade 5"))
                                val secion6 = sectionDao.insertSection(Section(6,"Grade 6"))
                                val secion7 = sectionDao.insertSection(Section(7,"Grade 7"))
                                val secion8 = sectionDao.insertSection(Section(8,"Grade 8"))
                                val secion9 = sectionDao.insertSection(Section(9,"Grade 9"))
                                val secion10 = sectionDao.insertSection(Section(10,"Grade 10"))


                                val english = subjectDao.insertSubject(Subject(1,"English"))
                                val maths = subjectDao.insertSubject(Subject(2,"Mathematics"))
                                val environmentalScience = subjectDao.insertSubject(Subject(3,"Environmental Science"))
                                val amharic = subjectDao.insertSubject(Subject(4,"Amharic"))
                                val sport = subjectDao.insertSubject(Subject(5,"Sport"))

                                val civics = subjectDao.insertSubject(Subject(6,"Civics"))
                                val socialScience = subjectDao.insertSubject(Subject(7,"Social Science"))
                                val basicScience = subjectDao.insertSubject(Subject(8,"Basic Science"))
                                val geography = subjectDao.insertSubject(Subject(9,"Geography"))
                                val history = subjectDao.insertSubject(Subject(10,"History"))
                                val music = subjectDao.insertSubject(Subject(11,"Music"))
                                val physics = subjectDao.insertSubject(Subject(12,"Physics"))
                                val biology = subjectDao.insertSubject(Subject(13,"Biology"))
                                val chemistry = subjectDao.insertSubject(Subject(14,"Chemistry"))

                                val subject1to4= listOf(english,maths,environmentalScience,music,amharic,sport)
                                val subject5to6= listOf(english,maths,civics,socialScience,music,amharic,sport,basicScience)
                                val subject7to8= listOf(english,maths,civics,socialScience,amharic,sport,physics,chemistry,biology)
                                val subject9to10= listOf(english,maths,civics,amharic,sport,physics,chemistry,biology,geography,history)



                                val grades = listOf(secion1,secion2,secion3,secion4,secion5,secion6,secion7,secion8,secion9,secion10)

                                var counter = 1L
                                grades.forEach {
                                    if(it<=4){
                                        for(i in subject1to4)
                                        {
                                            sectionSubjectDao.insertSectionSubject(SectionSubject(counter,i,it))
                                            counter++
                                        }

                                    }
                                    else if( it==5L ||it==6L)
                                    {
                                        for(i in subject5to6)
                                        {
                                            sectionSubjectDao.insertSectionSubject(SectionSubject(counter,i,it))
                                            counter++
                                        }
                                    }
                                    else if( it==7L ||it==8L)
                                    {
                                        for(i in subject7to8)
                                        {
                                            sectionSubjectDao.insertSectionSubject(SectionSubject(counter,i,it))
                                            counter++
                                        }
                                    }
                                    else if( it==9L ||it==10L)
                                    {
                                        for(i in subject9to10)
                                        {
                                            sectionSubjectDao.insertSectionSubject(SectionSubject(counter,i,it))
                                            counter++
                                        }
                                    }


                                }

                                val adminRole = Role(1,"ADMIN")
                                val teacherRole = Role(2,"TEACHER")
                                val studentRole = Role(3,"STUDENT")
                                val parentRole = Role(4,"PARENT")

                                roleDao.insertRole(adminRole)
                                roleDao.insertRole(teacherRole)
                                roleDao.insertRole(studentRole)
                                roleDao.insertRole(parentRole)

                                val test1 = AssessmentType(1,"Test 1",10.0)
                                val test2 = AssessmentType(2,"Test 2",10.0)
                                val assignment1 = AssessmentType(3,"Assignment 1",10.0)
                                val assignment2 = AssessmentType(4,"Assignment 2",10.0)
                                val midExam = AssessmentType(5,"Mid Exam",20.0)
                                val finalExam = AssessmentType(6,"Final Exam",40.0)

                                assessmentTypeDao.insertAssessmentType(test1)
                                assessmentTypeDao.insertAssessmentType(test2)
                                assessmentTypeDao.insertAssessmentType(assignment1)
                                assessmentTypeDao.insertAssessmentType(assignment2)
                                assessmentTypeDao.insertAssessmentType(midExam)
                                assessmentTypeDao.insertAssessmentType(finalExam)




                            }


                        }
                    })
                    .build()

                INSTANCE = instance
                return instance
            }
        }
        fun destroyInstance() {Long
            INSTANCE = null
        }

        fun populateDb(instance:ParcherDatabase){






        }
    }



}