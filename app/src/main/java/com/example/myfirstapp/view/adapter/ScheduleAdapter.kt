package com.example.myfirstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.view.ui.fragments.ScheduleFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val shceduleListener: ScheduleFragment): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    val listConference = ArrayList<Conference>()

    // We are saying, wich it's the article that we wants to design one item in our RecyclerViewer (item_schedule)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConference.size

    // We are saying what data we are going to have
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        // Showing in ui
        holder.conferenceName.text = conference.title
        holder.speakerName.text    = conference.speaker
        holder.scheduleTag.text    = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime

        val hourFormat = simpleDateFormat.format(conference.datetime)

        holder.conferenceHour.text = hourFormat
        holder.conferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener{
            shceduleListener.onConferenceClicked(conference, position)
        }

    }
    // This method allow to adding data at adapter
    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val conferenceName = itemView.findViewById<TextView>(R.id.conferenceName)
        val speakerName    = itemView.findViewById<TextView>(R.id.speakerName)
        val scheduleTag    = itemView.findViewById<TextView>(R.id.scheduleTag)
        val conferenceHour = itemView.findViewById<TextView>(R.id.conferenceHour)
        val conferenceAMPM = itemView.findViewById<TextView>(R.id.conferenceAMPM)
    }
}