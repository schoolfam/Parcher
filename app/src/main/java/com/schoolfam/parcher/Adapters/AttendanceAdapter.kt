package com.schoolfam.parcher.Adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.student_attendance_list_item.view.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.text.SimpleDateFormat

class AttendanceAdapter: RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {
    private var students = listOf<User>()
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var attendanceViewModel: AttendanceViewModel

    private lateinit var sectionViewModel: SectionViewModel

    private  var sectionId: Long? = null
    private  var date: String? = null
    private lateinit var attendance: List<Attendance>







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_attendance_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]



        holder.studentNameTextView.text = student.fname+" "+student.lname
        holder.studentEmailTextView.text = student.emailAddress
        holder.studentRoleTextView.text = position.toString()

        val count:Int = attendance.filter { attendance -> attendance.student_id==student.id}.size

        if (count!=0){
            holder.absentFab.id = R.id.fab_absent_attendance_button
            holder.absentFab.setImageResource(R.drawable.ic_clear_black_24dp)
            holder.absentFab.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF3C3C"))
        }

        holder.absentFab.setOnClickListener {
            when(it.id){

                R.id.fab_present_attendance_button -> {


                    val cdate = SimpleDateFormat("dd/MM/yyyy").parse(date)

                    val newAttendance = Attendance(student.id!!,false,sectionId!!,cdate)

                    attendanceViewModel.insertAttendance(newAttendance)


                    (it as FloatingActionButton).setImageResource(R.drawable.ic_clear_black_24dp)
                    it.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF3C3C")))
                    it.id = R.id.fab_absent_attendance_button

                    Snackbar.make(it, student.fname+" "+student.lname+" Is Absent",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                R.id.fab_absent_attendance_button -> {
                    val date = SimpleDateFormat("dd/MM/yyyy").parse(date)
                        val attendance = attendance.find { attendance -> attendance.student_id == student.id }
                        attendanceViewModel.deleteAdmin(attendance!!)


                    (it as FloatingActionButton).setImageResource(R.drawable.ic_check_black_24dp)
                    it.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFE4AD")))
                    it.id = R.id.fab_present_attendance_button

                    Snackbar.make(it, student.fname+" "+student.lname+" Is Present",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }

        }
    }
    fun replaceItems(students: List<User>,userViewModel: UserViewModel,studentViewModel: StudentViewModel,attendanceViewModel: AttendanceViewModel,sectionId:Long,date:String,attendance: List<Attendance>) {
        this.students = students
        this.userViewModel = userViewModel
        this.studentViewModel = studentViewModel
        this.attendanceViewModel = attendanceViewModel
        this.sectionId = sectionId
        this.date = date
        this.attendance=attendance
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val studentNameTextView = containerView.student_attendance_name_recylerview_item as TextView
        val studentEmailTextView = containerView.student_attendance_email_recylerview_item as TextView
        val studentRoleTextView = containerView.student_attendance_role_recylerview_item as TextView
        val absentFab =  containerView.fab_present_attendance_button as FloatingActionButton

    }
}