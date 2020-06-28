package com.example.remindme.screens.people

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.remindme.R
import com.example.remindme.database.PeopleDatabase
import com.example.remindme.databinding.PeopleFragmentBinding

class PeopleFragment : Fragment() {
    private lateinit var dataBinding: PeopleFragmentBinding
    lateinit var  viewModel:PeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater
            ,R.layout.people_fragment
            ,container
            ,false)

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application

        val dataSource = PeopleDatabase.getInstance(application).peopleDao

        val viewModelFactory = PeopleViewModelFactory(dataSource,application)

        val peopleViewModel : PeopleViewModel by viewModels{viewModelFactory}
        viewModel =peopleViewModel

        dataBinding.peopleViewModel = peopleViewModel
        dataBinding.lifecycleOwner = this

        val adapter =PeopleAdapter()
        dataBinding.peopleList.adapter = adapter

        peopleViewModel.people.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                dataBinding.tvEmpty.visibility = View.VISIBLE
                dataBinding.peopleList.visibility = View.GONE
                adapter.notifyDataSetChanged()
            }else {
                dataBinding.tvEmpty.visibility = View.GONE
                dataBinding.peopleList.visibility = View.VISIBLE
                adapter.submitList(it)
            }
        })

        dataBinding.fb.setOnClickListener {view:View ->
            view.findNavController().
            navigate(R.id.action_peopleFragment_to_addPersonFragment)
        }
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.people_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_menu_item -> deleteAllData()
        }
        return true
    }

    private fun deleteAllData() {
        viewModel.deleteAllPeople()
    }

    companion object {
        private const val TAG = "PeopleFragment"
    }
}