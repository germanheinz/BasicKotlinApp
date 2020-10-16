package com.example.myfirstapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myfirstapp.R
import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.view.adapter.ScheduleAdapter
import com.example.myfirstapp.view.adapter.ScheduleListener
import com.example.myfirstapp.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {

    // Variable to Access firebase an ViewModel
    private lateinit var viewModel      : ScheduleViewModel
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GET VIEWMODEL INSTANCE
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        // METHOD TO GET FIREBASE DATA
        viewModel.refresh()

        // GET ADAPTER INSTANCE
        scheduleAdapter = ScheduleAdapter(this)
        // SET ATTRIBUTE TO RECYCLERVIEW
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
        if(it != null) rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    /* We Implemented Listener interface, previously created. For move to another fragment
    * 1 - implement interface
    * 2 - create bundle variable
    * 3 - navigate and pass data to the other fragment
    * */
    override fun onConferenceClicked(conference: Conference, position: Int) {
       var bundle = bundleOf("conference" to conference)

        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }
}
