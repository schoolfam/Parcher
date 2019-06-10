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
import com.schoolfam.parcher.Fragments.TeacherFragments.TeacherPostAssessmentFragment
import com.schoolfam.parcher.Fragments.TeacherFragments.TeacherViewAssessmentFragment
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.student_assessment_list_item.view.*
import kotlinx.android.synthetic.main.student_attendance_list_item.view.*
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_email_recylerview_item
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_name_recylerview_item
import kotlinx.android.synthetic.main.student_attendance_list_item.view.student_attendance_role_recylerview_item
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.text.SimpleDateFormat

class AssessmentAdapter: RecyclerView.Adapter<AssessmentAdapter.ViewHolder>() {
    private var students = listOf<User>()
    private lateinit var fragmentManager: FragmentManager
    private  var sectionId: Long? = null







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_assessment_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]



        holder.studentNameTextView.text = student.fname+" "+student.lname
        holder.studentEmailTextView.text = student.emailAddress
        holder.studentRoleTextView.text = position.toString()

        holder.postAssessmentFab.setOnClickListener {

            val fragment = TeacherPostAssessmentFragment()
            val bundle = Bundle()
            bundle.putSerializable("current_student",student)
            bundle.putLong("section_id",sectionId!!)
            fragment.arguments = bundle
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.teacher_frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            Snackbar.make(it, student.fname+" "+student.lname+"'s Assessment Selected",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        holder.viewAssessmentFab.setOnClickListener {

            val fragment = TeacherViewAssessmentFragment()
            val bundle = Bundle()
            bundle.putSerializable("current_student",student)
            bundle.putLong("section_id",sectionId!!)
            fragment.arguments = bundle
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.teacher_frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            Snackbar.make(it, student.fname+" "+student.lname+"'s Assessment View Selected",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun fabOnclick(view: View){

    }

    fun replaceItems(students: List<User>,sectionId:Long,fragmentManager: FragmentManager) {
        this.students = students
        this.fragmentManager = fragmentManager
        this.sectionId =sectionId

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val studentNameTextView = containerView.student_attendance_name_recylerview_item as TextView
        val studentEmailTextView = containerView.student_attendance_email_recylerview_item as TextView
        val studentRoleTextView = containerView.student_attendance_role_recylerview_item as TextView
        val postAssessmentFab =  containerView.fab_post_assessment_button as FloatingActionButton
        val viewAssessmentFab =  containerView.fab_view_assessment_button as FloatingActionButton

    }
}