package com.example.remindme.screens.details

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.remindme.database.People
import com.example.remindme.database.PeopleDao
import kotlinx.coroutines.*

class PersonDetailsViewModel(
    application: Application,
    val dataSource: PeopleDao,
    personId: Long
) : ViewModel() {

    private val viewModelJOb = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJOb)

    private var person = MediatorLiveData<People>()
    fun getPerson() = person

    init {
        person.addSource(dataSource.getPerson(personId), person::setValue)
    }

    override fun onCleared() {
        uiScope.cancel()
    }
    companion object{
        private const val TAG = "PersonDetailsViewModel"
    }
}