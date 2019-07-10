package com.schoolfam.parcher

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.util.Pair
import android.view.View
import android.view.Window
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var splashImageView: ImageView
    private lateinit var appNameTextView: TextView
    private lateinit var appDescTextView: TextView
    private lateinit var appDesc2TextView: TextView

    private lateinit var fromBottomAnimation: Animation
    private lateinit var fromBottomAnimation1: Animation
    private lateinit var fromBottomAnimation2: Animation



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_splash)



        splashImageView = splashIcon
        appNameTextView = app_name_text_view
        appDescTextView = app_desc_text_view
        appDesc2TextView = app_desc2_text_view

        fromBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottomAnimation1 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottomAnimation2 = AnimationUtils.loadAnimation(this, R.anim.from_bottom)


        fromBottomAnimation1.startOffset = 100
        fromBottomAnimation2.startOffset = 200


        splashImageView.startAnimation(fromBottomAnimation)
        appNameTextView.startAnimation(fromBottomAnimation1)
        appDescTextView.startAnimation(fromBottomAnimation2)
        appDesc2TextView.startAnimation(fromBottomAnimation2)


        Handler().postDelayed({Pair(splashImageView,"appIconTransition")
            val splashIntent = Intent(this, LoginActivity::class.java)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
            {
                val imageViewPair = Pair.create<View,String>(splashImageView,"appIconTransition")
                val textViewPair = Pair.create<View,String>(appNameTextView,"appNameTransition")



                val pairs = ArrayList<Pair<View,String>>()

                pairs.add(imageViewPair)
                pairs.add(textViewPair)

                val pairArray: Array<Pair<View,String>> = pairs.toTypedArray()


                val option: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity,*pairArray)

                startActivity(splashIntent,option.toBundle())
                finish()
            }
            else
            {
                startActivity(splashIntent)
                finish()

            }

        }, 2000)




    }
}
