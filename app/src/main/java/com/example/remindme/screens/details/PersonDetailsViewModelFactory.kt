package com.example.remindme.screens.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remindme.database.PeopleDao
import java.lang.IllegalArgumentException

class PersonDetailsViewModelFactory (
    val application:Application,
    private val dataSource: PeopleDao,
    private val personId: Long
): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonDetailsViewModel::class.java)){
            return  PersonDetailsViewModel(application,dataSource,personId) as T
        }else
            throw  IllegalArgumentException("unknown ViewModel class")
    }
}