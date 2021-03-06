package com.schoolfam.parcher.Fragments.ParentFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Adapters.AttendanceAdapter
import com.schoolfam.parcher.Adapters.ParentStudentsAdapter

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_parent_children.*
import kotlinx.android.synthetic.main.fragment_teacher_attendance_fragmnet.*
import java.text.SimpleDateFormat

class ParentChildrenFragment : Fragment() {
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
        var binding : com.schoolfam.parcher.databinding.FragmentParentChildrenBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_children, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(view.context,arguments?.getString("current_date"), Toast.LENGTH_LONG).show()
        val currentParent = arguments?.getSerializable("current_teacher") as User?


        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        attendanceViewModel = ViewModelProviders.of(this).get(AttendanceViewModel::class.java)


        studentRecyclerView = parent_children_recycler_view as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)


        Toast.makeText(activity,"Current Teachers Name: "+currentParent!!.fname, Toast.LENGTH_LONG).show()

        userViewModel.findUserById(currentParent.id!!).observe(this, Observer {
                parent -> parent?.let{

            studentViewModel.findStudentByParentId(parent.id!!).observe(this, Observer {students->

                val users: MutableList<User> = mutableListOf()
                students.forEach {student->
                    var cuser: User?=null
                    userViewModel.findUserById(student.userId).observe(this, Observer {
                            user->
                        users.add(user)
                        val adapter = ParentStudentsAdapter()
                        val fragmentManager = activity!!.supportFragmentManager
                        adapter.replaceItems(users,fragmentManager)
                        studentRecyclerView.adapter = adapter
                        Toast.makeText(activity,"Number Students in this Parent: "+users.size, Toast.LENGTH_LONG).show()
                        if (users.isEmpty()){
                            Toast.makeText(activity,"No Students are registered for this parent", Toast.LENGTH_LONG).show()
                        }

                    })


                }


            })

        }
        })

    }

}
