package com.example.remindme.screens.people

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.AlarmManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.remindme.R
import com.example.remindme.database.PeopleDatabase
import com.example.remindme.databinding.PeopleFragmentBinding
import com.example.remindme.notifications.AlarmReceiver
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PeopleFragment : Fragment() {
    private lateinit var dataBinding: PeopleFragmentBinding
    lateinit var viewModel: PeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the view using dataBinding
        dataBinding = DataBindingUtil.inflate(
            inflater
            ,R.layout.people_fragment
            ,container
            ,false
        )

        //initialize the layout with options menu
        setHasOptionsMenu(true)

        //get the application context from the activity
        val application = requireNotNull(this.activity).application
        Log.i(TAG, "the context: ${application.applicationContext}")
        Log.i(TAG, "the context: $context")
        Log.i(TAG, "the context: ${context?.applicationContext}")

        //get an instance 'a singleton' from room database
        val dataSource = PeopleDatabase.getInstance(application).peopleDao

        val viewModelFactory = PeopleViewModelFactory(dataSource, application)

        val peopleViewModel: PeopleViewModel by viewModels { viewModelFactory }
        viewModel = peopleViewModel

        dataBinding.peopleViewModel = peopleViewModel
        dataBinding.lifecycleOwner = this

        // initialize the the adapter from PeopleAdapter class
        val adapter = PeopleAdapter(PersonClickListener { personId: Long ->
            peopleViewModel.onPersonClicked(personId)
        })
        // set the adapter for the recyclerView
        dataBinding.peopleList.adapter = adapter

        peopleViewModel.people.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                showEmptyState()
                dataBinding.parentLayout.setBackgroundColor(resources.getColor(R.color.emptyStateBackground))
//                dataBinding.parentLayout.setBackgroundColor(Color.WHITE)
                adapter.notifyDataSetChanged()
            } else {
                hideEmptyState()
//                dataBinding.parentLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
                adapter.submitList(it)
            }
        })

        dataBinding.fb.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_peopleFragment_to_addPersonFragment)
        }

        peopleViewModel.navigateToPersonDetails.observe(viewLifecycleOwner,
            Observer {person ->
            person?.let {
                this.findNavController().navigate(
                    PeopleFragmentDirections.actionPeopleFragmentToPersonDetailsFragment(person)
                )
                peopleViewModel.onPersonDetailsNavigated()
            }
        })
        return dataBinding.root
    }

    // the list isn't empty and we should hide the empty state views
    private fun hideEmptyState() {
        dataBinding.tvEmpty.visibility = View.GONE
        dataBinding.emptyStateImage.visibility = View.GONE
        dataBinding.peopleList.visibility = View.VISIBLE
    }

    // the list is empty, the empty state views will appear
    private fun showEmptyState() {
        dataBinding.tvEmpty.visibility = View.VISIBLE
        dataBinding.emptyStateImage.visibility = View.VISIBLE
        dataBinding.peopleList.visibility = View.GONE
    }
    // initialize the alarm manager to start the broadcast
    private fun initializeTheAlarm() {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager!!,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            10000,
            pendingIntent
        )
    }

    // initialize the options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.people_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dummy_data_menu_item -> addDummyData()
            R.id.delete_all_menu_item -> deleteAllData()
            R.id.setting_item -> this.findNavController()
                .navigate(R.id.action_peopleFragment_to_settingFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    /** this is helper method for test purpose to add a dummy data*/
    private fun addDummyData() {
        viewModel.AddDummyData()
    }

    /** delete all the data in the people table */
    private fun deleteAllData() {
        MaterialAlertDialogBuilder(context)
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.delete)) { dialog, _ ->
                viewModel.deleteAllPeople()
                dialog.cancel()
            }
            .show()
    }
    companion object {
        private const val TAG = "PeopleFragment"
    }
}