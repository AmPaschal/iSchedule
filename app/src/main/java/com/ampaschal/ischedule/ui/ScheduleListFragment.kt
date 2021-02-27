package com.ampaschal.ischedule.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ampaschal.ischedule.R
import com.ampaschal.ischedule.SmsScheduleApplication
import com.ampaschal.ischedule.adapters.ScheduleListAdapter
import com.ampaschal.ischedule.viewmodels.SmsScheduleViewModel
import com.ampaschal.ischedule.viewmodels.SmsScheduleViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleListFragment : Fragment() {

    private val scheduleViewModel: SmsScheduleViewModel by viewModels {
        SmsScheduleViewModelFactory((activity?.application as SmsScheduleApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_schedule)
        val scheduleAdapter = ScheduleListAdapter()
        recyclerView.adapter = scheduleAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Log.d(javaClass.simpleName, "onViewCreated called")

        scheduleViewModel.schedules.observe(viewLifecycleOwner, Observer { schedules ->
            schedules?.let {
                scheduleAdapter.submitData(schedules)
            }
        })

        val fabAddSchedule = view.findViewById<FloatingActionButton>(R.id.fab_add_schedule)
        fabAddSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleListFragment_to_AddScheduleFragment)
        }
    }
}