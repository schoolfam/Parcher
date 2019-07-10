package com.schoolfam.parcher.Fragments.AdminFragments



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.utility.ioThread
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_add_parent.view.*

class AddParentFragment : Fragment() {
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel

    private lateinit var parentFirstNameEditText: EditText
    private lateinit var parentLastNameEditText: EditText
    private lateinit var parentUserNameEditText: EditText
    private lateinit var parentEmailEditText: EditText
    private lateinit var parentPasswordEditText: EditText
    private lateinit var parentPhoneEditText: EditText
    private lateinit var parentGenderRadioGroup: RadioGroup
    private lateinit var parentMaleRadioButton: RadioButton
    private lateinit var parentFemaleRadioButton: RadioButton
    private lateinit var parentRegisterButton: Button





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
        return inflater.inflate(R.layout.fragment_add_parent, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFirstNameEditText = view.parent_first_name_edit_text
        parentLastNameEditText = view.parent_last_name_edit_text
        parentUserNameEditText = view.parent_user_name_edit_text
        parentUserNameEditText = view.parent_user_name_edit_text
        parentEmailEditText = view.parent_email_edit_text
        parentPasswordEditText = view.parent_password_edit_text
        parentPhoneEditText = view.parent_phone_edit_text
        parentGenderRadioGroup = view.parent_gender_radio_group
        parentMaleRadioButton = view.parent_male_radio_button
        parentFemaleRadioButton = view.parent_female_radio_button
        parentRegisterButton = view.parent_register_button


        parentRegisterButton.setOnClickListener {
            if (parentFirstNameEditText.text.toString().startsWith(" ")||parentFirstNameEditText.text.toString()==""||
                parentLastNameEditText.text.toString().startsWith(" ")||parentLastNameEditText.text.toString()==""||
                parentUserNameEditText.text.toString().startsWith(" ")||parentUserNameEditText.text.toString()==""||
                parentEmailEditText.text.toString().startsWith(" ")||parentEmailEditText.text.toString()==""||
                parentPasswordEditText.text.toString().startsWith(" ")||parentPasswordEditText.text.toString()==""){

                Snackbar.make(it, "Please Fill All Fields", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                val gender:String = if(parentMaleRadioButton.isChecked){
                    "Male"
                } else{
                    "Female"
                }


                val newUser = User(
                    parentFirstNameEditText.text.toString(),
                    parentLastNameEditText.text.toString(),
                    parentUserNameEditText.text.toString(),
                    parentEmailEditText.text.toString(),
                    parentPasswordEditText.text.toString(),
                    4, gender)
                Toast.makeText(activity,"Progress ", Toast.LENGTH_LONG).show()



                    val newParent = Parent( userViewModel.insertUser(newUser),parentPhoneEditText.text.toString().toLong())
                    parntViewModel.insertParent(newParent)

                    Snackbar.make(it, "You Have Successfully Registered PARENT: "+parentFirstNameEditText.text.toString(),Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

//                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
//                    val fragment = AddParentFragment()
//                    fragmentTransaction.replace(R.id.admin_nav_host_fragment, fragment)
//                    fragmentTransaction.addToBackStack(null)
//                    fragmentTransaction.commit()
//                    Navigation.createNavigateOnClickListener(R.id.action_addParentFragment_to_addStudentsFragment)
                    findNavController().navigate(R.id.action_global_addParentFragment)



            }

        }
    }
}
