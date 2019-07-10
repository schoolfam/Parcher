package com.schoolfam.parcher.Fragments.ParentFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_add_parent.view.*
import kotlinx.android.synthetic.main.fragment_parent_my_profile.*
import kotlinx.android.synthetic.main.fragment_parent_my_profile.view.*


class ParentMyProfileFragment : Fragment() {

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
    private lateinit var parentEditButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var parent:Parent
        val currentParent = arguments?.getSerializable("current_teacher") as User?




        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)


        parentFirstNameEditText = view.edit_parent_first_name_edit_text
        parentLastNameEditText = view.edit_parent_last_name_edit_text
        parentUserNameEditText = view.edit_parent_user_name_edit_text
        parentEmailEditText = view.edit_parent_email_edit_text
        parentPasswordEditText = view.edit_parent_password_edit_text
        parentPhoneEditText = view.edit_parent_phone_edit_text
        parentGenderRadioGroup = view.edit_parent_gender_radio_group
        parentMaleRadioButton = view.edit_parent_male_radio_button
        parentFemaleRadioButton = view.edit_parent_female_radio_button
        parentEditButton = view.parent_edit_button

        parentFirstNameEditText.setText(currentParent!!.fname)
        parentLastNameEditText.setText(currentParent.lname)
        parentUserNameEditText.setText(currentParent.username)
        parentEmailEditText.setText(currentParent.emailAddress)
        parentPasswordEditText.setText(currentParent.password)
        parntViewModel.allParents.observe(this, Observer { cp ->
            val parentPhone = cp.find { parent -> parent.user_id == currentParent.id }!!
                parentPhoneEditText.setText(parentPhone.phone_number.toString())
            })

        Toast.makeText(activity, currentParent.gender,Toast.LENGTH_LONG).show()
        if (currentParent.gender == "Male")
        {
            parentFemaleRadioButton.isChecked = false
            parentMaleRadioButton.isChecked = true

        }
        else{
            parentMaleRadioButton.isChecked = false
            parentFemaleRadioButton.isChecked = true
        }



        parentEditButton.setOnClickListener {
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
                currentParent.gender=gender



                currentParent.fname = parentFirstNameEditText.text.toString()
                currentParent.lname = parentLastNameEditText.text.toString()
                currentParent.username = parentUserNameEditText.text.toString()
                currentParent.emailAddress = parentEmailEditText.text.toString()
                currentParent.password = parentPasswordEditText.text.toString()
                parntViewModel.allParents.observe(this, Observer { cp ->
                    parent = cp.find { parent -> parent.user_id == currentParent.id }!!
                    parent.phone_number = parentPhoneEditText.text.toString().toLong()
                    parntViewModel.updateParent(parent)
                })

                userViewModel.updateUser(currentParent)
                Snackbar.make(it, "Profile Successfully Updated", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                }
            }

    }
}
