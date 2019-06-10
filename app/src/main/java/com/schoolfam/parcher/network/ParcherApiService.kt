package com.schoolfam.parcher.network

import com.schoolfam.parcher.data.SectionSubject.SectionSubject
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.role.Role
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.data.user.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ParcherApiService {


    // Teacher Api Service
    @GET("/api/teacher/")
    fun getAllTeachersAsync(): Deferred<Response<List<Teacher>>>

    @GET("/api/teacher/one/")
    fun getTeacherByIdAsync(@Query("id") id: Long): Deferred<Response<Teacher>>

    @POST("/api/teacher/register/")
    fun addTeacherAsync(@Body teacher: Teacher): Deferred<Response<Void>>

    @DELETE("/api/teacher/")
    fun deleteTeacherAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/teacher/")
    fun updateTeacherAsync(@Path("id") id: Long, @Body teacher: Teacher): Deferred<Response<Void>>



    // Student Api Service
    @GET("/api/student/")
    fun getAllStudentsAsync(): Deferred<Response<List<Student>>>

    @GET("/api/student/one/")
    fun getStudentByIdAsync(@Query("id") id: Long): Deferred<Response<Student>>

    @POST("/api/student/register/")
    fun addStudentAsync(@Body student: Student): Deferred<Response<Void>>

    @DELETE("/api/student/")
    fun deleteStudentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/student/")
    fun updateStudentAsync(@Path("id") id: Long, @Body student: Student): Deferred<Response<Void>>




    // Parent Api Service
    @GET("/api/parent/")
    fun getAllParentsAsync(): Deferred<Response<List<Parent>>>

    @GET("/api/parent/one/")
    fun getParentByIdAsync(@Query("id") id: Long): Deferred<Response<Parent>>

    @POST("/api/parent/register/")
    fun addParentAsync(@Body parent: Parent): Deferred<Response<Void>>

    @DELETE("/api/parent/")
    fun deleteParentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/parent/")
    fun updateParentAsync(@Path("id") id: Long, @Body parent: Parent): Deferred<Response<Void>>



    // Admin Api Service
    @GET("/api/admin/")
    fun getAllAdminsAsync(): Deferred<Response<List<Admin>>>

    @GET("/api/admin/one/")
    fun getAdminByIdAsync(@Query("id") id: Long): Deferred<Response<Admin>>

    @POST("/api/admin/register/")
    fun addAdminAsync(@Body admin: Admin): Deferred<Response<Void>>

    @DELETE("/api/admin/")
    fun deleteAdminAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/admin/")
    fun updateAdminAsync(@Path("id") id: Long, @Body admin: Admin): Deferred<Response<Void>>



    // User Api Service
    @GET("/api/user/")
    fun getAllUsersAsync(): Deferred<Response<List<User>>>

    @GET("/api/user/one/")
    fun getUserByIdAsync(@Query("id") id: Long): Deferred<Response<User>>

    @POST("/api/user/register/")
    fun addUserAsync(@Body user: User): Deferred<Response<Void>>

    @DELETE("/api/user/")
    fun deleteUserAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/user/")
    fun updateUserAsync(@Path("id") id: Long, @Body user: User): Deferred<Response<Void>>




    // Attendance Api Service
    @GET("/api/attendance/")
    fun getAllAttendancesAsync(): Deferred<Response<List<Attendance>>>

    @GET("/api/attendance/one/")
    fun getAttendanceByIdAsync(@Query("id") id: Long): Deferred<Response<Attendance>>

    @POST("/api/attendance/add/")
    fun addAttendanceAsync(@Body attendance: Attendance): Deferred<Response<Void>>

    @DELETE("/api/attendance/")
    fun deleteAttendanceAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/attendance/")
    fun updateAttendanceAsync(@Path("id") id: Long, @Body attendance: Attendance): Deferred<Response<Void>>



    // Assessment Api Service
    @GET("/api/assessment/")
    fun getAllAssessmentsAsync(): Deferred<Response<List<Assessment>>>

    @GET("/api/assessment/one/")
    fun getAssessmentByIdAsync(@Query("id") id: Long): Deferred<Response<Assessment>>

    @POST("/api/assessment/add/")
    fun addAssessmentAsync(@Body assessment: Assessment): Deferred<Response<Void>>

    @DELETE("/api/assessment/")
    fun deleteAssessmentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/assessment/")
    fun updateAssessmentAsync(@Path("id") id: Long, @Body assessment: Assessment): Deferred<Response<Void>>




    // Assessment Type Api Service
    @GET("/api/assessmentType/")
    fun getAllAssessmentTypesAsync(): Deferred<Response<List<AssessmentType>>>

    @GET("/api/assessmentType/one/")
    fun getAssessmentTypeByIdAsync(@Query("id") id: Long): Deferred<Response<AssessmentType>>

    @POST("/api/assessmentType/add/")
    fun addAssessmentTypeAsync(@Body assessmentType: AssessmentType): Deferred<Response<Void>>

    @DELETE("/api/assessmentType/")
    fun deleteAssessmentTypeAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/assessmentType/")
    fun updateAssessmentTypeAsync(@Path("id") id: Long, @Body assessmentType: AssessmentType): Deferred<Response<Void>>



    // Role Api Service
    @GET("/api/role/")
    fun getAllRolesAsync(): Deferred<Response<List<Role>>>

    @GET("/api/role/one/")
    fun getRoleByIdAsync(@Query("id") id: Long): Deferred<Response<Role>>

    @POST("/api/role/add/")
    fun addRoleAsync(@Body role: Role): Deferred<Response<Void>>

    @DELETE("/api/role/")
    fun deleteRoleAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/role/")
    fun updateRoleAsync(@Path("id") id: Long, @Body role: Role): Deferred<Response<Void>>



    // Section Api Service
    @GET("/api/section/")
    fun getAllSectionsAsync(): Deferred<Response<List<Section>>>

    @GET("/api/section/one/")
    fun getSectionByIdAsync(@Query("id") id: Long): Deferred<Response<Section>>

    @POST("/api/section/add/")
    fun addSectionAsync(@Body section: Section): Deferred<Response<Void>>

    @DELETE("/api/section/")
    fun deleteSectionAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/section/")
    fun updateSectionAsync(@Path("id") id: Long, @Body section: Section): Deferred<Response<Void>>




    // Subject Api Service
    @GET("/api/subject/")
    fun getAllSubjectsAsync(): Deferred<Response<List<Subject>>>

    @GET("/api/subject/one/")
    fun getSubjectByIdAsync(@Query("id") id: Long): Deferred<Response<Subject>>

    @POST("/api/subject/add/")
    fun addSubjectAsync(@Body subject: Subject): Deferred<Response<Void>>

    @DELETE("/api/subject/")
    fun deleteSubjectAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/subject/")
    fun updateSubjectAsync(@Path("id") id: Long, @Body subject: Subject): Deferred<Response<Void>>



    // SectionSubject Api Service
    @GET("/api/sectionSubject/")
    fun getAllSectionSubjectsAsync(): Deferred<Response<List<SectionSubject>>>

    @GET("/api/sectionSubject/one/")
    fun getSectionSubjectByIdAsync(@Query("id") id: Long): Deferred<Response<SectionSubject>>

    @POST("/api/sectionSubject/add/")
    fun addSectionSubjectAsync(@Body sectionSubject: SectionSubject): Deferred<Response<Void>>

    @DELETE("/api/sectionSubject/")
    fun deleteSectionSubjectAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/sectionSubject/")
    fun updateSectionSubjectAsync(@Path("id") id: Long, @Body sectionSubject: SectionSubject): Deferred<Response<Void>>


    // Announcement Api Service
    @GET("/api/announcement/")
    fun getAllAnnouncementsAsync(): Deferred<Response<List<Announcement>>>

    @GET("/api/announcement/one/")
    fun getAnnouncementByIdAsync(@Query("id") id: Long): Deferred<Response<Announcement>>

    @POST("/api/announcement/add/")
    fun addAnnouncementAsync(@Body announcement: Announcement): Deferred<Response<Void>>

    @DELETE("/api/announcement/")
    fun deleteAnnouncementAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/announcement/")
    fun updateAnnouncementAsync(@Path("id") id: Long, @Body announcement: Announcement): Deferred<Response<Void>>

}