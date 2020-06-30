package com.example.remindme.screens.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remindme.database.PeopleDao
import kotlinx.coroutines.*

class PeopleViewModel(
    val dataSource: PeopleDao,
    application: Application
) : AndroidViewModel(application) {

    private val _navigateToPersonDetails = MutableLiveData<Long>()
    val navigateToPersonDetails: LiveData<Long>
        get() = _navigateToPersonDetails
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val people = dataSource.getAllPeople()

    fun deleteAllPeople() {
        uiScope.launch {
            clear()
        }
    }

    fun onPersonClicked(personId: Long) {
        _navigateToPersonDetails.value = personId
    }

    fun onPersonDetailsNavigated(){
        _navigateToPersonDetails.value = null
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()
        }
    }
}