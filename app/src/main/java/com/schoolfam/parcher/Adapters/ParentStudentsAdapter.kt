package com.schoolfam.parcher.Adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Fragments.ParentFragments.ParentAttendanceMenu
import com.schoolfam.parcher.Fragments.ParentFragments.ParentViewAssessmentFragment
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.student_attendance_list_item.view.*
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_email_recylerview_item
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_name_recylerview_item
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_role_recylerview_item
import kotlinx.android.synthetic.main.student_list_item.view.*
import kotlinx.android.synthetic.main.student_parent_list_item.view.*
import java.text.SimpleDateFormat

class ParentStudentsAdapter: RecyclerView.Adapter<ParentStudentsAdapter.ViewHolder>() {
    private var students = listOf<User>()
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var attendanceViewModel: AttendanceViewModel
    private lateinit var fragmentManager: FragmentManager


    private lateinit var sectionViewModel: SectionViewModel

    private  var sectionId: Long? = null
    private  var date: String? = null
    private lateinit var attendance: List<Attendance>







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_parent_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]


        holder.studentNameTextView.text = student.fname+" "+student.lname
        holder.studentEmailTextView.text = student.emailAddress
        holder.studentRoleTextView.text = position.toString()

        holder.assessmentFab.setOnClickListener {


            val fragment = ParentViewAssessmentFragment()
            val bundle = Bundle()
            bundle.putSerializable("current_student",student)
            fragment.arguments = bundle
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            Snackbar.make(it, student.fname+" "+student.lname+"'s Assessment Selected",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        holder.attendanceFab.setOnClickListener {
            val fragment = ParentAttendanceMenu()
            val bundle = Bundle()
            bundle.putSerializable("current_student",student)
            fragment.arguments = bundle
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            Snackbar.make(it, student.fname+" "+student.lname+"'s Attendance Selected",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    fun replaceItems(students: List<User>,fragmentManager: FragmentManager) {
        this.students = students
        this.fragmentManager = fragmentManager
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val studentNameTextView = containerView.student_attendance_name_recylerview_item as TextView
        val studentEmailTextView = containerView.student_attendance_email_recylerview_item as TextView
        val studentRoleTextView = containerView.student_attendance_role_recylerview_item as TextView
        val assessmentFab =  containerView.fab_child_assessment_button as FloatingActionButton
        val attendanceFab =  containerView.fab_child_attendance_button as FloatingActionButton

    }
}