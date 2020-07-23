package com.example.remindme.screens.people

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remindme.database.People
import com.example.remindme.database.PeopleDao
import com.example.remindme.groupOfPeople
import kotlinx.coroutines.*
import kotlin.math.log

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

    fun AddDummyData(){
        uiScope.launch {
            addAll()
        }
    }
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

    private suspend fun addAll(){
        withContext(Dispatchers.IO){
            val data = groupOfPeople()
            Log.i(TAG, "addAll: ${data[0]}")
            Log.i(TAG, "addAll: ${data.toTypedArray()[0]}")
            dataSource.insertAll(*data.toTypedArray())
        }
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()
        }
    }
    
    companion object{
         const val TAG = "PeopleViewModel"
    }
}