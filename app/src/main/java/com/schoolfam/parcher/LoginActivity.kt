package com.schoolfam.parcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.AdminViewModel
import com.schoolfam.parcher.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var fromBottomAnimation: Animation
    private lateinit var fromBottomAnimation1: Animation
    private lateinit var fromBottomAnimation2: Animation
    private lateinit var fromBottomAnimation3: Animation

    private lateinit var loginButton:Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginDescTextView: TextView

    private lateinit var userViewModel:UserViewModel
    private lateinit var adminViewModel:AdminViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = login_button
        emailEditText = email_edit_text
        passwordEditText = password_edit_text
        loginDescTextView = login_desc_text_view


        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        adminViewModel = ViewModelProviders.of(this).get(AdminViewModel::class.java)

        userViewModel.allUsers.observe(this, Observer {
                users -> users?.let{ Toast.makeText(this,"The Number Of Users is: "+users.size,Toast.LENGTH_LONG).show()}
        })
        adminViewModel.findAdminById(1).observe(this, Observer {
                users -> users?.let{
            var adminUser : User
            userViewModel.findUserById(users.userId).observe(this,
                Observer {
                        admin -> admin?.let{adminUser = admin
                    Toast.makeText(this,"The Name of the Admin Is: "+ adminUser.fname,Toast.LENGTH_LONG).show()}
                })
        }



        })



        fromBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottomAnimation1 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottomAnimation2 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottomAnimation3 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)

        fromBottomAnimation1.startOffset = 50
        fromBottomAnimation2.startOffset = 70
        fromBottomAnimation3.startOffset = 90

        loginDescTextView.startAnimation(fromBottomAnimation)
        emailEditText.startAnimation(fromBottomAnimation1)
        passwordEditText.startAnimation(fromBottomAnimation2)
        loginButton.startAnimation(fromBottomAnimation3)

        loginButton.setOnClickListener {

           // userViewModel.insertUser(User(1,"Sinkumen","Asefa","Cnku","123456","sinkumen@gmail.com",1,"Male"))
            Toast.makeText(this,"User Added",Toast.LENGTH_LONG).show()
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)

    }
}
