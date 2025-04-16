package com.wilfredo.poke.module.ui.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wilfredo.poke.module.domain.model.Location

class LocationViewModel : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private val db = Firebase.firestore

    init {
        listenToLocations()
    }

    private fun listenToLocations() {
        db.collection("locations")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(10)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("Firestore", "Error escuchando cambios", error)
                    return@addSnapshotListener
                }

                val locationList = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Location::class.java)
                } ?: emptyList()

                _locations.postValue(locationList)
            }
    }
}