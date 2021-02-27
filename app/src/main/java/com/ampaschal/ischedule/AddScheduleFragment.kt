package com.ampaschal.ischedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ampaschal.ischedule.entities.SmsSchedule
import com.ampaschal.ischedule.viewmodels.SmsScheduleViewModel
import com.ampaschal.ischedule.viewmodels.SmsScheduleViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddScheduleFragment : Fragment() {

    private val scheduleViewModel: SmsScheduleViewModel by viewModels {
        SmsScheduleViewModelFactory((activity?.application as SmsScheduleApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTitle = view.findViewById<EditText>(R.id.et_title)
        val etMessage = view.findViewById<EditText>(R.id.et_message)
        val etRecipient = view.findViewById<EditText>(R.id.et_recipient)
        val etDateScheduled = view.findViewById<EditText>(R.id.et_date_scheduled)
        val btSaveSchedule = view.findViewById<Button>(R.id.bt_save_schedule)

        btSaveSchedule.setOnClickListener {

            val title = etTitle.text.trim().toString()
            val message = etMessage.text.trim().toString()
            val recipient = etRecipient.text.trim().toString()
            val date = etDateScheduled.text.trim().toString()

            val schedule = SmsSchedule(title, message, recipient, date)
            scheduleViewModel.insertSchedule(schedule)

            findNavController().popBackStack()

        }
    }
}