package com.example.remindme.screens.edit

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remindme.database.People
import com.example.remindme.database.PeopleDao
import kotlinx.coroutines.*

class AddPersonViewModel(
    val dataSource: PeopleDao,
    val application: Application
) : ViewModel() {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        Log.i("AddPersonViewModel", "AddPersonViewModel started!")
    }


    private suspend fun insert(result: People) {
        withContext(Dispatchers.IO) {
            dataSource.insert(result)
        }
    }

    fun insertPerson(result: People) {
        uiScope.launch {
            insert(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }

    companion object {
        private const val TAG = "AddPersonViewModel"
    }
}