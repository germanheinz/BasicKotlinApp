package com.example.myfirstapp.view.adapter

import com.example.myfirstapp.model.Conference


interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}