package com.schoolfam.parcher.Fragments.ParentFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.schoolfam.parcher.R


class ParentMyProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentParentMyProfileBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_my_profile, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
