package com.schoolfam.parcher

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.schoolfam.parcher.Fragments.ParentFragments.ParentChildrenFragment
import com.schoolfam.parcher.Fragments.ParentFragments.ParentMyProfileFragment
import com.schoolfam.parcher.Fragments.ParentFragments.ParentViewAnnouncementFragment
import com.schoolfam.parcher.data.user.User

class ParentActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private lateinit var fragment: Fragment
    var parent: User? = null
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                fragment = ParentChildrenFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                fragment.arguments = bundle
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                fragment = ParentMyProfileFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                fragment.arguments = bundle
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                fragment = ParentViewAnnouncementFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                fragment.arguments = bundle
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        parent = intent.extras.getSerializable("currentAdmin") as? User
        Toast.makeText(this,"Current User Name: "+parent!!.fname, Toast.LENGTH_LONG).show()

        fragment = ParentChildrenFragment()
        val bundle = Bundle()
        bundle.putSerializable("current_teacher",parent)
        fragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.parent_frame_layout, fragment)
        fragmentTransaction.commit()

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
