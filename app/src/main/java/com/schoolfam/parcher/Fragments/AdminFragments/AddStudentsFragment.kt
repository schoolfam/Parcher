package com.schoolfam.parcher.Fragments.AdminFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.utility.ioThread
import com.schoolfam.parcher.viewModel.*
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
        val view  = inflater.inflate(R.layout.fragment_add_students, container, false)

        return inflater.inflate(R.layout.fragment_add_students, container, false)
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

                val sectionId:Long = (studentSectionSpinner.selectedItem as Section).id
                val parenId:Long? = (studentParentSpinner.selectedItem as User).id
                val gender:String = if(studentMaleRadioButton.isChecked){
                    "Male"
                } else{
                    "Female"
                }


                val newUser = User(
                    studentFirstNameEditText.text.toString(),
                    studentLastNameEditText.text.toString(),
                    studentUserNameEditText.text.toString(),
                    studentEmailEditText.text.toString(),
                    studentPasswordEditText.text.toString(),
                    3, gender)
                Toast.makeText(activity,"Progress ",Toast.LENGTH_LONG).show()

                    val newStudent= Student( userViewModel.insertUser(newUser),parenId,sectionId)
                    studentViewModel.insertStudent(newStudent)

                    Snackbar.make(it, "You Have Successfully Registered STUDENT: "+studentFirstNameEditText.text.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    Snackbar.make(it, "Section Id: "+newStudent.section_id, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    findNavController().navigate(R.id.action_global_addStudentsFragment)


            }
        }


        userViewModel.allUsers.observe(this, Observer { parents -> parents?.let {
            val allParents:List<User> = it
            val adapter:ArrayAdapter<User> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,allParents.filter { user -> user.role_id == 4L }.reversed())
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
