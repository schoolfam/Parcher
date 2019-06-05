package com.schoolfam.parcher.Fragments.AdminFragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.viewModel.UserViewModel

class AddStudentsFragment : Fragment() {

    companion object {
        fun newInstance() = AddStudentsFragment()
    }

    //private lateinit var ViewModel: AddStudentsViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_students_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

}
