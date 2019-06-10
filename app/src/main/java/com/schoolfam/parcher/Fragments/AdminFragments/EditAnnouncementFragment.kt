package com.schoolfam.parcher.Fragments.AdminFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.viewModel.AnnouncementViewModel
import kotlinx.android.synthetic.main.fragment_edit_announcement.*
import kotlinx.android.synthetic.main.fragment_post_announcement.*
import kotlinx.android.synthetic.main.fragment_teacher_view_assessment.*


class EditAnnouncementFragment : Fragment() {

    private lateinit var announcementViewModel: AnnouncementViewModel
    private lateinit var announcementTitleEditText: EditText
    private lateinit var announcementDescEditText: EditText
    private lateinit var editAnnouncementButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_announcement, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementViewModel = ViewModelProviders.of(this).get(AnnouncementViewModel::class.java)
        announcementTitleEditText = edit_announcement_title
        announcementDescEditText = edit_announcement_desc
        editAnnouncementButton = edit_announcement_button

        val announcement = arguments?.getSerializable("current_announcement") as Announcement?

        announcementTitleEditText.setText(announcement!!.title)
        announcementDescEditText.setText(announcement!!.description)

        editAnnouncementButton.setOnClickListener {mView->
            if (announcementTitleEditText.text.toString().startsWith(" ")||
                announcementTitleEditText.text.toString() == ""||
                announcementDescEditText.text.toString().startsWith(" ") ||
                announcementDescEditText.text.toString() == ""
            ){
                Snackbar.make(mView, "Please Fill All Fields", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else{
                announcementViewModel.findAdminById(announcement!!.id!!).observe(this, Observer {
                    val mAnnouncement = it
                    mAnnouncement.title = announcementTitleEditText.text.toString()
                    mAnnouncement.description = announcementDescEditText.text.toString()

                    announcementViewModel.updateAnnouncement(mAnnouncement)
                    Snackbar.make(mView, "Announcement Edited Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    val fragment = ViewAnouncementFragment()
                    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                })
            }


        }



    }
}
