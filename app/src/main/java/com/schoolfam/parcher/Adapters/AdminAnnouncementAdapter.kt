package com.schoolfam.parcher.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.Fragments.AdminFragments.EditAnnouncementFragment
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.viewModel.AnnouncementViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.admin_announcement_list_item.view.*
import kotlinx.android.synthetic.main.announcement_list_item.view.announcement_date_recylerview_item
import kotlinx.android.synthetic.main.announcement_list_item.view.announcement_description_recylerview_item
import kotlinx.android.synthetic.main.announcement_list_item.view.announcement_title_recylerview_item

class AdminAnnouncementAdapter: RecyclerView.Adapter<AdminAnnouncementAdapter.ViewHolder>(){
    private var announcements = listOf<Announcement>()
    private lateinit var fragmentManager: FragmentManager
    private lateinit var announcementViewModel: AnnouncementViewModel
    private lateinit var navController: NavController


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.admin_announcement_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val annoncement = announcements[position]


        if (announcements.isEmpty()){
            Snackbar.make(holder.announcementTitle," No Announcement Posted Yet",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        else{
            holder.announcementTitle.text = annoncement.title
            holder.announcementDesc.text = annoncement.description
            holder.announcementDate.text = annoncement.date.toString()

            holder.editAnnouncementMenuButton.setOnClickListener {
//
//                val fragment = EditAnnouncementFragment()
                val bundle = Bundle()
                bundle.putSerializable("current_announcement",annoncement)
//                fragment.arguments = bundle
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.admin_nav_host_fragment, fragment)
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()

                navController.navigate(R.id.action_viewAnouncementFragment_to_editAnnouncementFragment,bundle)


                Snackbar.make(it," Editing This Announcement",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            holder.deleteAnnouncementButton.setOnClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setTitle("Delete Assessment")
                builder.setMessage("Are you sure You want to DELETE this Assessment?")
                builder.setPositiveButton("YES"){dialog, which ->
                    announcementViewModel.deleteAnnouncement(annoncement)
                    Snackbar.make(it," Announcement Deleted Successfully",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
                builder.setNeutralButton("Cancel"){_,_ ->

                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
        }


    }

    fun replaceItems(announcements: List<Announcement>,fragmentManager: FragmentManager,announcementViewModel: AnnouncementViewModel,navController: NavController) {
        this.announcements = announcements
        this.fragmentManager = fragmentManager
        this.announcementViewModel = announcementViewModel
        this.navController = navController
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = announcements.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val announcementTitle = containerView.announcement_title_recylerview_item as TextView
        val announcementDesc = containerView.announcement_description_recylerview_item as TextView
        val announcementDate = containerView.announcement_date_recylerview_item as TextView
        val editAnnouncementMenuButton = containerView.edit_announcement_menu_button as Button
        val deleteAnnouncementButton = containerView.delete_announcement_button as Button

    }
}