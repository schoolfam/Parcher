package com.schoolfam.parcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = login_button
        emailEditText = email_edit_text
        passwordEditText = password_edit_text
        loginDescTextView = login_desc_text_view




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
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)

    }
}
