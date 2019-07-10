package com.schoolfam.parcher.Fragments.AdminFragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.MainActivity


import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.utility.ioThread
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_add_teachers.view.*


class AddTeachersFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel

    private lateinit var teacherFirstNameEditText: EditText
    private lateinit var teacherLastNameEditText: EditText
    private lateinit var teacherUserNameEditText: EditText
    private lateinit var teacherEmailEditText: EditText
    private lateinit var teacherPasswordEditText: EditText
    private lateinit var teacherGenderRadioGroup: RadioGroup
    private lateinit var teacherMaleRadioButton: RadioButton
    private lateinit var teacherFemaleRadioButton: RadioButton
    private lateinit var teacherSectionSpinner: Spinner
    private lateinit var teacherRegisterButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentAddTeacherBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_teachers, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)

        teacherFirstNameEditText = view.teacher_first_name_edit_text
        teacherLastNameEditText = view.teacher_last_name_edit_text
        teacherUserNameEditText = view.teacher_user_name_edit_text
        teacherEmailEditText = view.teacher_email_edit_text
        teacherPasswordEditText = view.teacher_password_edit_text
        teacherGenderRadioGroup = view.teacher_gender_radio_group
        teacherMaleRadioButton = view.teacher_male_radio_button
        teacherFemaleRadioButton = view.teacher_female_radio_button
        teacherSectionSpinner = view.teacher_section_spinner
        teacherRegisterButton = view.teacher_register_button


        teacherRegisterButton.setOnClickListener {
            if (teacherFirstNameEditText.text.toString().startsWith(" ")||teacherFirstNameEditText.text.toString()==""||
                teacherLastNameEditText.text.toString().startsWith(" ")||teacherFirstNameEditText.text.toString()==""||
                teacherUserNameEditText.text.toString().startsWith(" ")||teacherFirstNameEditText.text.toString()==""||
                teacherEmailEditText.text.toString().startsWith(" ")||teacherFirstNameEditText.text.toString()==""||
                teacherPasswordEditText.text.toString().startsWith(" ")||teacherFirstNameEditText.text.toString()==""){

                Snackbar.make(it, "Please Fill All Fields", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                var gender:String = "Male"
                val sectionId:Long = (teacherSectionSpinner.selectedItem as Section).id
                teacherGenderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    gender = if(checkedId == 1){
                        "Male"
                    } else{
                        "Female"
                    }
                }


                val newUser = User(
                    teacherFirstNameEditText.text.toString(),
                    teacherLastNameEditText.text.toString(),
                    teacherUserNameEditText.text.toString(),
                    teacherEmailEditText.text. toString(),
                    teacherPasswordEditText.text.toString(),
                    2, gender)
                    Toast.makeText(activity,"Progress ",Toast.LENGTH_LONG).show()
                clearFields()

                    ioThread{
                        val newTeacher:Teacher = Teacher( userViewModel.insertUser(newUser),sectionId)
                        teacherViewModel.insertTeacher(newTeacher)

                        Snackbar.make(it, "You Have Successfully Registered TEACHER: "+teacherFirstNameEditText.text.toString()+
                                "\nWith User Id: "+userViewModel.currentUser+
                                "\nAnd Teacher Id: "+teacherViewModel.currentTeacher+
                                "\nFor Section: "+(teacherSectionSpinner.selectedItem as Section).sectionName, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()



                        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                        val fragment = AddTeachersFragment()
                        fragmentTransaction.replace(R.id.frame_layout, fragment)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()


                    }

            }
        }



        sectionViewModel.allSection.observe(this, Observer { sections -> sections?.let{ lst ->
            val allgrades: List<Section> = lst
            val grades: MutableList<Section> = mutableListOf()

            allgrades.forEach{ section->
                teacherViewModel.allTeachers.observe(this, Observer { it ->

                    if (it.size>0){
                        var counter = 0
                        it.forEach{
                            if(it.sectionId == section.id){
                                counter++
                            }
                        }
                        if(counter ==0){grades.add(section)}
                        val adapter: ArrayAdapter<Section> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,grades)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        teacherSectionSpinner.adapter = adapter



                    }
                    else{
                        val adapter: ArrayAdapter<Section> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,allgrades)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        teacherSectionSpinner.adapter = adapter
                    }



                })
            }
            grades.clear()




        } })

    }

    private fun clearFields() {
        teacherFirstNameEditText.setText("")
        teacherLastNameEditText.setText("")
        teacherUserNameEditText.setText("")
        teacherEmailEditText.setText("")
        teacherPasswordEditText.setText("")
        sectionViewModel.allSection.observe(this, Observer { sections -> sections?.let{ lst ->
            val allgrades: List<Section> = lst
            val grades: MutableList<Section> = mutableListOf()

            allgrades.forEach{ section->
                teacherViewModel.allTeachers.observe(this, Observer { it ->

                    if (it.size>0){
                        var counter = 0
                        it.forEach{
                            if(it.sectionId == section.id){
                                counter++
                            }
                        }
                        if(counter ==0){grades.add(section)}
                        val adapter: ArrayAdapter<Section> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,grades)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        teacherSectionSpinner.adapter = adapter



                    }
                    else{
                        val adapter: ArrayAdapter<Section> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,allgrades)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        teacherSectionSpinner.adapter = adapter
                    }



                })
            }
            grades.clear()




        } })



    }


}
