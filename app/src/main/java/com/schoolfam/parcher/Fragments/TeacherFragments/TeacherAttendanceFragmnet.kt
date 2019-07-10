package com.schoolfam.parcher.Fragments.TeacherFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Adapters.AttendanceAdapter
import com.schoolfam.parcher.Adapters.StudentAdapter

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_teacher_attendance_fragmnet.*
import kotlinx.android.synthetic.main.fragment_teacher_students.*
import java.text.SimpleDateFormat


class TeacherAttendanceFragmnet : Fragment() {
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var attendanceViewModel: AttendanceViewModel
    private lateinit var dateTextView: TextView


    private lateinit var studentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentTeacherAttendanceBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_teacher_attendance_fragmnet, container, false)
        var myView : View  = binding.root

        return myView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(view.context,arguments?.getString("current_date"),Toast.LENGTH_LONG).show()
        val currentTeacher = arguments?.getSerializable("current_teacher") as User?
        val date = SimpleDateFormat("dd/MM/yyyy").parse(arguments?.getString("current_date"))

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel::class.java)
        dateTextView =date_text_view
        dateTextView.text = arguments?.getString("current_date")

        studentRecyclerView = teacher_attendance_recycler_view as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)


        Toast.makeText(activity,"Current Teachers Name: "+currentTeacher!!.fname,Toast.LENGTH_LONG).show()

        teacherViewModel.findTeacherByUserId(currentTeacher.id!!).observe(this, Observer {
                teacher -> teacher?.let{

            studentViewModel.findStudentBySectionId(teacher.sectionId).observe(this, Observer {students->

                val users: MutableList<User> = mutableListOf()
                students.forEach {student->
                    var cuser: User?=null
                    userViewModel.findUserById(student.userId).observe(this, Observer {
                            user->
                        users.add(user)
                        val adapter = AttendanceAdapter()
                        attendanceViewModel.findAttendanceByDateAndSection(date.time,teacher.sectionId).observe(this, Observer {
                            val attendance = it
                            Toast.makeText(activity,"Number Of Attendance: "+ attendance.size,Toast.LENGTH_LONG).show()
                            if(attendance.isEmpty()){
                                Snackbar.make(view, "All Students are Present In This Day",
                                    Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show()
                            }
                            adapter.replaceItems(users,userViewModel,studentViewModel,attendanceViewModel,teacher.sectionId,arguments?.getString("current_date")!!,attendance)
                        })

                        studentRecyclerView.adapter = adapter
                        Toast.makeText(activity,"Number Students in this Class: "+users.size,Toast.LENGTH_LONG).show()
                        if (users.isEmpty()){
                            Toast.makeText(activity,"No Students in this Class",Toast.LENGTH_LONG).show()
                        }

                    })


                }


            })

        }
        })
    }

}
