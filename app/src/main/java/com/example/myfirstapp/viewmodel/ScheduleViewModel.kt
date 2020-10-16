package com.example.myfirstapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.network.Callback
import com.example.myfirstapp.network.FirestoreService

class ScheduleViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }
            override fun onFailed(result: List<Conference>?) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}