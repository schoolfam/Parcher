package com.schoolfam.parcher.Fragments.ParentFragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_parent_child_attendance.*
import kotlinx.android.synthetic.main.fragment_teacher_attendance_fragmnet.*
import java.text.SimpleDateFormat

class ParentChildAttendanceFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var attendanceViewModel: AttendanceViewModel
    private lateinit var dateTextView: TextView
    private lateinit var studentTextView: TextView
    private lateinit var statusTextView: TextView

    private  var currentStudent:User? = null
    private lateinit var cDate:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_child_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(view.context,arguments?.getString("current_date"), Toast.LENGTH_LONG).show()
         currentStudent = arguments?.getSerializable("current_student") as User?
        val date = SimpleDateFormat("dd/MM/yyyy").parse(arguments?.getString("current_date"))

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel::class.java)
        dateTextView =dateCheck
        studentTextView = studentCheck
        statusTextView = statusCheck
        dateTextView.text = arguments?.getString("current_date")
        studentTextView.text =currentStudent!!.fname+" "+currentStudent!!.lname

        attendanceViewModel.findAttendanceByDateAndStudent(date.time,currentStudent!!.id!!).observe(this, Observer {
            if(it!=null){
                statusTextView.text = "STATUS: ABSENT"
                statusTextView.setTextColor(Color.parseColor("#FF3C3C"))
            }
            else{
                statusTextView.text = "STATUS: PRESENT"
                statusTextView.setTextColor(Color.parseColor("#4AD161"))
            }
        })
    }



}
