package com.example.remindme.screens.edit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.remindme.R
import com.example.remindme.database.People
import com.example.remindme.database.PeopleDatabase
import com.example.remindme.databinding.AddPersonFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat


class AddPersonFragment : Fragment() {

    private lateinit var dataBinding: AddPersonFragmentBinding
    private lateinit var viewModelFactory: AddPersonViewModelFactory
    private lateinit var people: People

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_person_fragment, container, false
        )

        Log.i(TAG,"AddPersonFragment started!")
        val application =  requireNotNull(this.activity).application

        val dataSource = PeopleDatabase.getInstance(application).peopleDao
        viewModelFactory = AddPersonViewModelFactory(dataSource,application)
        val viewModel: AddPersonViewModel by viewModels{viewModelFactory}

        dataBinding.bDone.setOnClickListener {view:View ->
            if (getData()) {
                viewModel.insertPerson(people)
                view.findNavController().navigate(R.id.action_addPersonFragment_to_peopleFragment)
            }
        }

        val textInputEditText = dataBinding.etDate


        textInputEditText.setOnClickListener {
            showDateDialog(dataBinding.etDate)
        }

        return dataBinding.root

    }

    private fun getData() :Boolean{
        val firstName = dataBinding.etFirstName.text.toString().trim()
        if (firstName ==""){
            dataBinding.etFirstName.error = getString(R.string.error_message)
            Log.e(TAG, getString(R.string.error_message))
            return false
        }
        val secondName = dataBinding.etSecondName.text.toString().trim()
        if (secondName == "" ){
            dataBinding.etSecondName.error = getString(R.string.error_message)
            Log.e(TAG,getString(R.string.error_message))
            return false
        }
        val place = dataBinding.etPlace.text.toString().trim()
        if (place == ""){
            dataBinding.etPlace.error = getString(R.string.error_message)
            return false
        }
        val date = dataBinding.etDate.text.toString().trim()
        val note = dataBinding.etNote.text.toString().trim()
        val gender = getGender()
        people = People(firstName = firstName,
            secondName = secondName,place = place,
            time = date,note = note,gender = gender)
        return true
    }

    private fun getGender(): String {
        val gender = dataBinding.rgGender.checkedRadioButtonId
        return when(gender){
            R.id.rb_male -> "Male"
            R.id.rb_female -> "Female"
            else -> "Unknown"
        }
    }

    private fun showDateDialog(editTextDate: TextInputEditText) {
        val calendar = java.util.Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                        calendar[Calendar.MINUTE] = minute
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
                        editTextDate.setText(simpleDateFormat.format(calendar.time))
                    }
                TimePickerDialog(
                    context,
                    timeSetListener,
                    calendar[Calendar.HOUR_OF_DAY],
                    calendar[Calendar.MINUTE],
                    false
                ).show()
            }

        DatePickerDialog(
            requireNotNull(context),
            dateSetListener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }
    companion object {
        private const val TAG = "AddPersonFragment"
    }
}