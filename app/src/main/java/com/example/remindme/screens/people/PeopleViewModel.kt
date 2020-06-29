package com.example.remindme.screens.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.remindme.database.PeopleDao
import kotlinx.coroutines.*

class PeopleViewModel(
    val dataSource: PeopleDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val people = dataSource.getAllPeople()

    fun deleteAllPeople() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()
        }
    }
}