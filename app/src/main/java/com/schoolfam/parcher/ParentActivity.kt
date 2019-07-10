package com.schoolfam.parcher

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.schoolfam.parcher.Fragments.ParentFragments.ParentChildrenFragment
import com.schoolfam.parcher.Fragments.ParentFragments.ParentMyProfileFragment
import com.schoolfam.parcher.Fragments.ParentFragments.ParentViewAnnouncementFragment
import com.schoolfam.parcher.data.user.User
import kotlinx.android.synthetic.main.activity_parent.*

class ParentActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private lateinit var fragment: Fragment
    private lateinit var navController: NavController
    var parent: User? = null
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_my_childs -> {

                fragment = ParentChildrenFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                navController.navigate(R.id.action_global_parentChildrenFragment,bundle)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my_profile -> {

                fragment = ParentMyProfileFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                navController.navigate(R.id.action_global_parentMyProfileFragment,bundle)


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                fragment = ParentViewAnnouncementFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_teacher",parent)
                navController.navigate(R.id.action_global_parentViewAnnouncementFragment,bundle)

                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.parent_nav_host_fragment)

        parent = intent.extras.getSerializable("currentAdmin") as? User
        Toast.makeText(this,"Current User Name: "+parent!!.fname, Toast.LENGTH_LONG).show()


        val bundle = Bundle()
        bundle.putSerializable("current_teacher",parent)
        navController.navigate(R.id.action_global_parentChildrenFragment,bundle)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }
}
