package com.schoolfam.parcher.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.user.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentAdapter: RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
    private var students = listOf<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.studentNameTextView.text = student.fname+" "+student.lname
        holder.studentEmailTextView.text = student.emailAddress
        holder.studentRoleTextView.text = position.toString()
    }

    fun replaceItems(students: List<User>) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
    {
        val studentNameTextView = containerView.student_name_recylerview_item as TextView
        val studentEmailTextView = containerView.student_email_recylerview_item as TextView
        val studentRoleTextView = containerView.student_role_recylerview_item as TextView

    }
}