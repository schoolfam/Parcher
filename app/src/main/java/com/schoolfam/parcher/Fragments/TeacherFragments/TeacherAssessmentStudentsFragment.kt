package com.schoolfam.parcher.Fragments.TeacherFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Adapters.AssessmentAdapter
import com.schoolfam.parcher.Adapters.AttendanceAdapter
import com.schoolfam.parcher.Adapters.ParentStudentsAdapter

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_parent_children.*
import kotlinx.android.synthetic.main.fragment_parent_children.date_text_view
import kotlinx.android.synthetic.main.fragment_teacher_assessment_students.*
import kotlinx.android.synthetic.main.fragment_teacher_attendance_fragmnet.*
import java.text.SimpleDateFormat


class TeacherAssessmentStudentsFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var attendanceViewModel: AttendanceViewModel
    private lateinit var studentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_assessment_students, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentTeacher = arguments?.getSerializable("current_teacher") as User?

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel::class.java)


        studentRecyclerView = teacher_assessment_recycler_view as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)


        Toast.makeText(activity,"Current Teachers Name: "+currentTeacher!!.fname,Toast.LENGTH_LONG).show()

        teacherViewModel.findTeacherByUserId(currentTeacher.id!!).observe(this, Observer {
                teacher -> teacher?.let{

            studentViewModel.findStudentBySectionId(teacher.section_id).observe(this, Observer {students->

                val users: MutableList<User> = mutableListOf()
                students.forEach {student->
                    userViewModel.findUserById(student.user_id).observe(this, Observer {
                            user->
                        users.add(user)
                        val adapter = AssessmentAdapter()
                        adapter.replaceItems(users,teacher.section_id,activity!!.supportFragmentManager,findNavController())

                        studentRecyclerView.adapter = adapter
                        Toast.makeText(activity,"Number Students in this Class: "+users.size,Toast.LENGTH_LONG).show()
                        Toast.makeText(activity,"Section: "+teacher.section_id,Toast.LENGTH_LONG).show()
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
