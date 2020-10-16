package com.example.myfirstapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.model.Speaker
import com.example.myfirstapp.network.Callback
import com.example.myfirstapp.network.FirestoreService

class SpeakerViewModel : ViewModel() {

    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    private fun getSpeakerFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }
            override fun onFailed(result: List<Speaker>?) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}