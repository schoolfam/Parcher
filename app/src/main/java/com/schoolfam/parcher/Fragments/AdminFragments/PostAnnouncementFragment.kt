package com.schoolfam.parcher.Fragments.AdminFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.viewModel.AnnouncementViewModel
import kotlinx.android.synthetic.main.fragment_post_announcement.*
import java.text.SimpleDateFormat
import java.util.*


class PostAnnouncementFragment : Fragment() {

    private lateinit var announcementViewModel: AnnouncementViewModel
    private lateinit var announcementTitleEditText: EditText
    private lateinit var announcementDescEditText: EditText
    private lateinit var postAnnouncementButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Data Binding
        var binding : com.schoolfam.parcher.databinding.FragmentPostAnnouncementBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_announcement, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementViewModel = ViewModelProviders.of(this).get(AnnouncementViewModel::class.java)
        announcementTitleEditText = announcement_title_edit_text
        announcementDescEditText = announcement_desc_edit_text
        postAnnouncementButton = post_announcement_button

        postAnnouncementButton.setOnClickListener {
            if (announcementTitleEditText.text.toString().startsWith(" ")||
                announcementTitleEditText.text.toString() == ""||
                announcementDescEditText.text.toString().startsWith(" ") ||
                announcementDescEditText.text.toString() == ""
            ){
                Snackbar.make(it, "Please Fill All Fields", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month =c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                var currentDate = "$day/$month/$year"
                val date = SimpleDateFormat("dd/MM/yyyy").parse(currentDate)
                val newAnnouncement = Announcement(announcementTitleEditText.text.toString(),announcementDescEditText.text.toString(),date)

                announcementViewModel.insertAnnouncement(newAnnouncement)
                Snackbar.make(it, "Announcement Posted Successfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
//                val fragment = ViewAnouncementFragment()
//                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.admin_nav_host_fragment, fragment)
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
                findNavController().navigate(R.id.action_global_viewAnouncementFragment)

            }
        }



    }
}
