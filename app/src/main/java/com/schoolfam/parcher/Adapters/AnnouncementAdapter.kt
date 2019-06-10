package com.schoolfam.parcher.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.user.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.announcement_list_item.view.*
import kotlinx.android.synthetic.main.student_list_item.view.*

class AnnouncementAdapter: RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>(){
    private var announcements = listOf<Announcement>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.announcement_list_item, parent, false)
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
        }

    }

    fun replaceItems(announcements: List<Announcement>) {
        this.announcements = announcements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = announcements.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val announcementTitle = containerView.announcement_title_recylerview_item as TextView
        val announcementDesc = containerView.announcement_description_recylerview_item as TextView
        val announcementDate = containerView.announcement_date_recylerview_item as TextView

    }
}