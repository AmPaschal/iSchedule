package com.ampaschal.ischedule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ampaschal.ischedule.R
import com.ampaschal.ischedule.entities.SmsSchedule

class ScheduleListAdapter: ListAdapter<SmsSchedule, ScheduleListAdapter.ScheduleViewHolder>(ScheduleComparator()) {

    private var schedules: List<SmsSchedule> = mutableListOf()



    class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvDateSchedule: TextView = itemView.findViewById(R.id.tv_scheduled_date)

        fun bind(smsSchedule: SmsSchedule) {
            tvTitle.text = smsSchedule.title
            tvDateSchedule.text = smsSchedule.date
        }

        companion object {
            fun create(parent: ViewGroup): ScheduleViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_schedule, parent, false)

                return ScheduleViewHolder(view)
            }
        }
    }

    class ScheduleComparator: DiffUtil.ItemCallback<SmsSchedule>() {
        override fun areItemsTheSame(oldItem: SmsSchedule, newItem: SmsSchedule): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SmsSchedule, newItem: SmsSchedule): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules.get(position)
        holder.bind(schedule)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    fun submitData(schedules: List<SmsSchedule>) {
        this.schedules = schedules
        notifyDataSetChanged()
    }

}