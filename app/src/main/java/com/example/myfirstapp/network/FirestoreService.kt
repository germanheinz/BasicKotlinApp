package com.example.myfirstapp.network

import com.example.myfirstapp.model.Conference
import com.example.myfirstapp.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCE_COLLECTION_NAME = "conference"
const val SPEAKER_COLLECTION_NAME = "speaker"

class FirestoreService{

    // Connection to Firestore
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        // Get data in Offline mode
        firebaseFirestore.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKER_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    var list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCE_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    var list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}