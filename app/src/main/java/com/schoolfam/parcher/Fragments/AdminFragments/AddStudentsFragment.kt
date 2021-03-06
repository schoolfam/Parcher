package com.schoolfam.parcher.Fragments.AdminFragments

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.utility.ioThread
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_add_students.*
import kotlinx.android.synthetic.main.fragment_add_students.view.*


class AddStudentsFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel



    private lateinit var studentFirstNameEditText: EditText
    private lateinit var studentLastNameEditText: EditText
    private lateinit var studentUserNameEditText: EditText
    private lateinit var studentEmailEditText: EditText
    private lateinit var studentPasswordEditText: EditText
    private lateinit var studentGenderRadioGroup: RadioGroup
    private lateinit var studentMaleRadioButton: RadioButton
    private lateinit var studentFemaleRadioButton: RadioButton
    private lateinit var studentRegisterButton: Button


    private lateinit var studentSectionSpinner: Spinner
    private lateinit var studentParentSpinner: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)






        }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentAddStudentBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_students, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentFirstNameEditText = view.student_first_name_edit_text
        studentLastNameEditText = view.student_last_name_edit_text
        studentUserNameEditText = view.student_user_name_edit_text
        studentEmailEditText = view.student_email_edit_text
        studentPasswordEditText = view.student_password_edit_text
        studentGenderRadioGroup = view.gender_radio_group
        studentMaleRadioButton = view.male_radio_button
        studentFemaleRadioButton = view.female_radio_button
        studentSectionSpinner = view.section_spinner
        studentParentSpinner = view.parent_spinner
        studentRegisterButton = view.student_register_button

        studentRegisterButton.setOnClickListener {
            if (studentFirstNameEditText.text.toString().startsWith(" ")||studentFirstNameEditText.text.toString()==""||
                studentLastNameEditText.text.toString().startsWith(" ")||studentLastNameEditText.text.toString()==""||
                studentUserNameEditText.text.toString().startsWith(" ")||studentUserNameEditText.text.toString()==""||
                studentEmailEditText.text.toString().startsWith(" ")||studentEmailEditText.text.toString()==""||
                studentPasswordEditText.text.toString().startsWith(" ")||studentPasswordEditText.text.toString()==""){

                Snackbar.make(it, "Please Fill All Fields", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                var gender:String = "Male"
                val sectionId:Long = (studentSectionSpinner.selectedItem as Section).id
                val parenId:Long? = (studentParentSpinner.selectedItem as User).id
                studentGenderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    gender = if(checkedId == 1){
                        "Male"
                    } else{
                        "Female"
                    }
                }


                val newUser = User(
                    studentFirstNameEditText.text.toString(),
                    studentLastNameEditText.text.toString(),
                    studentUserNameEditText.text.toString(),
                    studentEmailEditText.text.toString(),
                    studentPasswordEditText.text.toString(),
                    3, gender)
                Toast.makeText(activity,"Progress ",Toast.LENGTH_LONG).show()


                ioThread{
                    val newStudent= Student( userViewModel.insertUser(newUser),parenId,sectionId)
                    studentViewModel.insertStudent(newStudent)

                    Snackbar.make(it, "You Have Successfully Registered STUDENT: "+studentFirstNameEditText.text.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    Snackbar.make(it, "Section Id: "+newStudent.sectionId, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    val fragment = AddStudentsFragment()
                    fragmentTransaction.replace(R.id.frame_layout, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()



                }
            }
        }


        userViewModel.allUsers.observe(this, Observer { parents -> parents?.let {
            val allParents:List<User> = it
            val adapter:ArrayAdapter<User> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,allParents.filter { user -> user.roleId == 4L }.reversed())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            studentParentSpinner.adapter = adapter
        } })

        sectionViewModel.allSection.observe(this, Observer { section -> section?.let{
            val grades: List<Section> = it
            val adapter: ArrayAdapter<Section> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,grades)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            studentSectionSpinner.adapter = adapter


        } })

    }


    fun getSelectedGrade(view: View ):Section
    {
       return studentSectionSpinner.selectedItem as Section
    }

   }
