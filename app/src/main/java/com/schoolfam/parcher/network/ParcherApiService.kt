package com.schoolfam.parcher.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import com.schoolfam.parcher.utility.JsonDateDeserializer




interface ParcherApiService {

    // Admin Api Service
    @GET("/api/admin/")
    fun getAllAdminsAsync(): Deferred<Response<List<Admin>>>

    @GET("/api/admin/one/{id}")
    fun getAdminByIdAsync(@Path("id") id: Long): Deferred<Response<Admin>>

    @POST("/api/admin/register/")
    fun addAdminAsync(@Body admin: Admin): Deferred<Response<Void>>

    @DELETE("/api/admin/{id}")
    fun deleteAdminAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/admin/{id}")
    fun updateAdminAsync(@Path("id") id: Long, @Body admin: Admin): Deferred<Response<Void>>




    // Announcement Api Service
    @GET("api/announcement/")
    fun getAllAnnouncementsAsync(): Deferred<Response<List<Announcement>>>

    @GET("api/announcement/one/")
    fun getAnnouncementByIdAsync(@Query("id") id: Long): Deferred<Response<Announcement>>

    @POST("api/announcement/add/")
    fun addAnnouncementAsync(@Body announcement: Announcement): Deferred<Response<Void>>

    @DELETE("api/announcement/{id}")
    fun deleteAnnouncementAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/announcement/")
    fun updateAnnouncementAsync( @Body announcement: Announcement): Deferred<Response<Void>>




    // Assessment Api Service
    @GET("api/assessment/")
    fun getAllAssessmentsAsync(): Deferred<Response<List<Assessment>>>

    @GET("api/assessment/one/")
    fun getAssessmentByIdAsync(@Query("id") id: Long): Deferred<Response<Assessment>>

    @GET("api/assessment/one/")
    fun getAssessmentByStudentIdAndAssessementTypeIdAsync(@Query("assessmentTypeId") assessmentTypeId:Long,
                                                          @Query("studentId") studentId:Long): Deferred<Response<Assessment>>

    @GET("api/assessment/one/")
    fun getAssessmentByStudentIdAndSubjectIdAsync(@Query("subjectId") subjectId:Long,
                                                  @Query("studentId") studentId:Long): Deferred<Response<Assessment>>

    @POST("api/assessment/add/")
    fun addAssessmentAsync(@Body assessment: Assessment): Deferred<Response<Void>>

    @DELETE("api/assessment/{id}")
    fun deleteAssessmentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/assessment/")
    fun updateAssessmentAsync(@Body assessment: Assessment): Deferred<Response<Void>>




    // Assessment Type Api Service
    @GET("/api/assessmentType/")
    fun getAllAssessmentTypesAsync(): Deferred<Response<List<AssessmentType>>>

    @GET("/api/assessmentType/one/")
    fun getAssessmentTypeByIdAsync(@Query("id") id: Long): Deferred<Response<AssessmentType>>

    @POST("/api/assessmentType/add/")
    fun addAssessmentTypeAsync(@Body assessmentType: AssessmentType): Deferred<Response<Void>>

    @DELETE("/api/assessmentType/{id}")
    fun deleteAssessmentTypeAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/assessmentType/{id}")
    fun updateAssessmentTypeAsync(@Path("id") id: Long, @Body assessmentType: AssessmentType): Deferred<Response<Void>>




    // Attendance Api Service
    @GET("api/attendance/")
    fun getAllAttendancesAsync(): Deferred<Response<List<Attendance>>>

    @GET("api/attendance/one/")
    fun getAttendanceByIdAsync(@Query("id") id: Long): Deferred<Response<Attendance>>

    @GET("api/attendance/one/")
    fun getAttendanceByDateAndStudentAsync(@Query("date") date:Long,
                                           @Query("studentId") studentId:Long): Deferred<Response<Attendance>>

    @GET("api/attendance/by_date/")
    fun getAttendanceByDateAndSectionAsync(@Query("date") date:Long,
                                           @Query("sectionId") sectionId:Long): Deferred<Response<List<Attendance>>>

    @POST("api/attendance/add/")
    fun addAttendanceAsync(@Body attendance: Attendance): Deferred<Response<Void>>

    @DELETE("api/attendance/{id}")
    fun deleteAttendanceAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/attendance/{id}")
    fun updateAttendanceAsync(@Path("id") id: Long, @Body attendance: Attendance): Deferred<Response<Void>>



    // Parent Api Service
    @GET("api/parent/")
    fun getAllParentsAsync(): Deferred<Response<List<Parent>>>

    @GET("api/parent/one/")
    fun getParentByIdAsync(@Query("id") id: Long): Deferred<Response<Parent>>

    @POST("api/parent/register/")
    fun addParentAsync(@Body parent: Parent): Deferred<Response<Void>>

    @DELETE("api/parent/{id}")
    fun deleteParentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/parent/{id}")
    fun updateParentAsync(@Path("id") id: Long, @Body parent: Parent): Deferred<Response<Void>>




    // Role Api Service
    @GET("/api/role/")
    fun getAllRolesAsync(): Deferred<Response<List<Role>>>

    @GET("/api/role/one/")
    fun getRoleByIdAsync(@Query("id") id: Long): Deferred<Response<Role>>

    @POST("/api/role/add/")
    fun addRoleAsync(@Body role: Role): Deferred<Response<Void>>

    @DELETE("/api/role/{id}")
    fun deleteRoleAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/role/{id}")
    fun updateRoleAsync(@Path("id") id: Long, @Body role: Role): Deferred<Response<Void>>



    // Section Api Service
    @GET("/api/section/")
    fun getAllSectionsAsync(): Deferred<Response<List<Section>>>

    @GET("/api/section/one/")
    fun getSectionByIdAsync(@Query("id") id: Long): Deferred<Response<Section>>

    @POST("/api/section/add/")
    fun addSectionAsync(@Body section: Section): Deferred<Response<Void>>

    @DELETE("/api/section/{id}")
    fun deleteSectionAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/section/{id}")
    fun updateSectionAsync(@Path("id") id: Long, @Body section: Section): Deferred<Response<Void>>




    // SectionSubject Api Service
    @GET("/api/sectionSubject/")
    fun getAllSectionSubjectsAsync(): Deferred<Response<List<SectionSubject>>>

    @GET("/api/sectionSubject/one/")
    fun getSectionSubjectByIdAsync(@Query("id") id: Long): Deferred<Response<SectionSubject>>

    @GET("/api/sectionSubject/by_section/")
    fun getSectionSubjectBySectionIdAsync(@Query("sectionId") sectionId: Long): Deferred<Response<List<SectionSubject>>>

    @POST("/api/sectionSubject/add/")
    fun addSectionSubjectAsync(@Body sectionSubject: SectionSubject): Deferred<Response<Void>>

    @DELETE("/api/sectionSubject/{id}")
    fun deleteSectionSubjectAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/sectionSubject/{id}")
    fun updateSectionSubjectAsync(@Path("id") id: Long, @Body sectionSubject: SectionSubject): Deferred<Response<Void>>



    // Student Api Service
    @GET("api/student/")
    fun getAllStudentsAsync(): Deferred<Response<List<Student>>>

    @GET("api/student/one/")
    fun getStudentByIdAsync(@Query("id") id: Long): Deferred<Response<Student>>

    @GET("api/student/by_section/")
    fun getStudentBySectionAsync(@Query("sectionId") sectionId: Long): Deferred<Response<List<Student>>>

    @GET("api/student/by_parent/")
    fun getStudentByParentAsync(@Query("parentId") parentId: Long): Deferred<Response<List<Student>>>

    @POST("api/student/register/")
    fun addStudentAsync(@Body student: Student): Deferred<Response<Void>>

    @DELETE("api/student/{id}")
    fun deleteStudentAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/student/{id}")
    fun updateStudentAsync(@Path("id") id: Long, @Body student: Student): Deferred<Response<Void>>



    // Subject Api Service
    @GET("/api/subject/")
    fun getAllSubjectsAsync(): Deferred<Response<List<Subject>>>

    @GET("/api/subject/one/")
    fun getSubjectByIdAsync(@Query("id") id: Long): Deferred<Response<Subject>>

    @POST("/api/subject/add/")
    fun addSubjectAsync(@Body subject: Subject): Deferred<Response<Void>>

    @DELETE("/api/subject/{id}")
    fun deleteSubjectAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("/api/subject/{id}")
    fun updateSubjectAsync(@Path("id") id: Long, @Body subject: Subject): Deferred<Response<Void>>



    // Teacher Api Service
    @GET("api/teacher/")
    fun getAllTeachersAsync(): Deferred<Response<List<Teacher>>>

    @GET("api/teacher/one/")
    fun getTeacherByIdAsync(@Query("id") id: Long): Deferred<Response<Teacher>>

    @POST("api/teacher/register/")
    fun addTeacherAsync(@Body teacher: Teacher): Deferred<Response<Void>>

    @DELETE("api/teacher/{id}")
    fun deleteTeacherAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/teacher/{id}")
    fun updateTeacherAsync(@Path("id") id: Long, @Body teacher: Teacher): Deferred<Response<Void>>




    // User Api Service
    @GET("api/user/")
    fun getAllUsersAsync(): Deferred<Response<List<User>>>

    @GET("api/user/one/")
    fun getUserByIdAsync(@Query("id") id: Long): Deferred<Response<User>>

    @GET("api/user/one/")
    fun getUserByEmailAsync(@Query("email") email: String): Deferred<Response<User>>

    @POST("api/user/register/")
    fun addUserAsync(@Body user: User): Deferred<Response<User>>

    @DELETE("api/user/{id}")
    fun deleteUserAsync(@Path("id") id: Long): Deferred<Response<Void>>

    @PUT("api/user/")
    fun updateUserAsync( @Body user: User): Deferred<Response<Void>>



    companion object {

        private val baseUrl = "http://10.6.211.72/cnku/parcher/public/index.php/"

        fun getInstance(): ParcherApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC


            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

            val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(ParcherApiService::class.java)

        }

    }









}