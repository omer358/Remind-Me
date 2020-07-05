package com.example.remindme.screens.people

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.remindme.R
import com.example.remindme.database.PeopleDatabase
import com.example.remindme.databinding.PeopleFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PeopleFragment : Fragment() {
    private lateinit var dataBinding: PeopleFragmentBinding
    lateinit var viewModel: PeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(
            inflater
            , R.layout.people_fragment
            , container
            , false
        )

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application

        val dataSource = PeopleDatabase.getInstance(application).peopleDao

        val viewModelFactory = PeopleViewModelFactory(dataSource, application)

        val peopleViewModel: PeopleViewModel by viewModels { viewModelFactory }
        viewModel = peopleViewModel

        dataBinding.peopleViewModel = peopleViewModel
        dataBinding.lifecycleOwner = this

        val adapter = PeopleAdapter(PersonClickListener { personId: Long ->
            peopleViewModel.onPersonClicked(personId)
        })
        dataBinding.peopleList.adapter = adapter

        peopleViewModel.people.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                dataBinding.tvEmpty.visibility = View.VISIBLE
                dataBinding.emptyStateImage.visibility = View.VISIBLE
                dataBinding.peopleList.visibility = View.GONE
                dataBinding.parentLayout.setBackgroundColor(Color.WHITE)
                adapter.notifyDataSetChanged()
            } else {
                dataBinding.tvEmpty.visibility = View.GONE
                dataBinding.emptyStateImage.visibility = View.GONE
                dataBinding.peopleList.visibility = View.VISIBLE
                dataBinding.parentLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
                adapter.submitList(it)
            }
        })

        dataBinding.fb.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_peopleFragment_to_addPersonFragment)
        }

        peopleViewModel.navigateToPersonDetails.observe(viewLifecycleOwner, Observer {person ->
            person?.let {
                this.findNavController().navigate(
                    PeopleFragmentDirections.actionPeopleFragmentToPersonDetailsFragment(person)
                )
                peopleViewModel.onPersonDetailsNavigated()
            }
        })
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.people_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_menu_item -> deleteAllData()
        }
        return true
    }

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