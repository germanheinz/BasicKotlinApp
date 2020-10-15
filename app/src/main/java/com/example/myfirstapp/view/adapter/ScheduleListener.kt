package com.example.myfirstapp.view.adapter

import android.telecom.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}