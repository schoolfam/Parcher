package com.schoolfam.parcher.Fragments.TeacherFragments


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoolfam.parcher.Adapters.StudentAdapter

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_teacher_students.*

class TeacherStudentsFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel


    private lateinit var studentRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_teacher_students, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)

        studentRecyclerView = student_recycler_view as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)

        val currentTeacher = arguments?.getSerializable("current_teacher") as User?
        Toast.makeText(activity,"Current Teachers Name: "+currentTeacher!!.fname,Toast.LENGTH_LONG).show()

        teacherViewModel.findTeacherByUserId(currentTeacher.id!!).observe(this, Observer {
            teacher -> teacher?.let{

            studentViewModel.findStudentBySectionId(teacher.sectionId).observe(this, Observer {students->

                val users: MutableList<User> = mutableListOf()
                students.forEach {student->
                    var cuser:User?=null
                    userViewModel.findUserById(student.userId).observe(this, Observer {
                        user->
                        users.add(user)
                        val adapter = StudentAdapter()
                        adapter.replaceItems(users)
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
