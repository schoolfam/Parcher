package com.schoolfam.parcher.Fragments.ParentFragments

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.schoolfam.parcher.Fragments.TeacherFragments.TeacherAttendanceFragmnet

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import kotlinx.android.synthetic.main.fragment_parent_attendance_menu.*
import kotlinx.android.synthetic.main.fragment_teacher_attendance_menu.*
import java.util.*


class ParentAttendanceMenu : Fragment() {

    private lateinit var todayFab: FloatingActionButton
    private lateinit var customFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentParentAttendanceMenuBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_attendance_menu, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayFab = parent_current_date
        customFab= parent_custom_date_picker

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        var currentDate:String = "$day/$month/$year"
        val attendanceFragment = ParentChildAttendanceFragment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        todayFab.setOnClickListener {
            bundle.putString("current_date",currentDate)
            bundle.putSerializable("current_student",arguments?.getSerializable("current_student") as User?)
            attendanceFragment.arguments = bundle
            fragmentTransaction.replace(R.id.parent_frame_layout, attendanceFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }




        customFab.setOnClickListener {
            val picker = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, dayOfMonth ->
                currentDate = "$dayOfMonth/$mMonth/$mYear"
                bundle.putString("current_date",currentDate)
                bundle.putSerializable("current_student",arguments?.getSerializable("current_student") as User?)
                attendanceFragment.arguments = bundle
                fragmentTransaction.replace(R.id.parent_frame_layout, attendanceFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

            },year,month,day)

            picker.show()
        }
    }

}
