package com.example.myfirstapp.view.adapter

import android.telecom.Conference
import com.example.myfirstapp.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}