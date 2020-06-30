package com.example.remindme.screens.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remindme.database.PeopleDao

class PersonDetailsViewModel(
    application: Application,
    dataSource: PeopleDao,
    personId: Long
) : ViewModel() {

}