package com.schoolfam.parcher.Fragments.AdminFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.schoolfam.parcher.Adapters.AdminAnnouncementAdapter
import com.schoolfam.parcher.Adapters.AnnouncementAdapter

import com.schoolfam.parcher.R
import com.schoolfam.parcher.viewModel.AnnouncementViewModel
import kotlinx.android.synthetic.main.fragment_parent_view_announcement.*
import kotlinx.android.synthetic.main.fragment_view_anouncement.*

class ViewAnouncementFragment : Fragment() {

    private lateinit var announcementRecyclerView: RecyclerView
    private lateinit var announcementViewModel: AnnouncementViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : com.schoolfam.parcher.databinding.FragmentViewAnnouncementBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_anouncement, container, false)
        var myView : View  = binding.root

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementRecyclerView = admin_announcement_recycler_view as RecyclerView
        announcementRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        announcementViewModel = ViewModelProviders.of(this).get(AnnouncementViewModel::class.java)

        announcementViewModel.allAnnoncements.observe(this, Observer {
            val adapter = AdminAnnouncementAdapter()
            adapter.replaceItems(it.reversed(),activity!!.supportFragmentManager,announcementViewModel)
            announcementRecyclerView.adapter = adapter

        })
    }
}
