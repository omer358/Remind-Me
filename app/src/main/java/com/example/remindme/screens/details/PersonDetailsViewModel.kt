package com.example.remindme.screens.details

import android.app.Application
import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.remindme.database.People
import com.example.remindme.database.PeopleDao
import com.example.remindme.sendNotification
import kotlinx.coroutines.*

class PersonDetailsViewModel(
    val application: Application,
    val dataSource: PeopleDao,
    personId: Long
) : ViewModel() {

    private val viewModelJOb = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJOb)

    private var person = MediatorLiveData<People>()
    fun getPerson() = person

    private val _navigateBackEvent = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean>
        get() = _navigateBackEvent

    init {
        person.addSource(dataSource.getPerson(personId), person::setValue)
    }

    fun startNotification(){
        val notificationManager = ContextCompat.getSystemService(application,
            NotificationManager::class.java)
        notificationManager?.sendNotification(application, person.value!!)
    }
    fun deletePerson(id: Long){
        uiScope.launch {
            Log.i(TAG,"deletePerson fun is running!")
            delete(id)
        }
        _navigateBackEvent.value = true
        Log.i(TAG,"Navigation event is $_navigateBackEvent")
    }

    private  suspend fun delete(id: Long){
        withContext(Dispatchers.IO){
            Log.i(TAG,"suspended delete fun is running!")
            dataSource.removePerson(id)
            Log.i(TAG,"the operation performed!")
        }
    }

    fun doneNavigateBack(){
        _navigateBackEvent.value = false
        Log.i(TAG,"doneNavigation fun")
    }

    override fun onCleared() {
        uiScope.cancel()
    }
    companion object{
        private const val TAG = "PersonDetailsViewModel"
    }
}