package com.example.remindme.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.remindme.R
import com.example.remindme.database.PeopleDatabase
import com.example.remindme.databinding.PersonDetailsFragmentBinding
import kotlinx.android.synthetic.main.people_list_item.*

class PersonDetailsFragment : Fragment() {

    private val args: PersonDetailsFragmentArgs by navArgs()
    private lateinit var dataBinding: PersonDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.person_details_fragment,parent,false)
        val application = requireNotNull(this.activity).application
        val dataSource = PeopleDatabase.getInstance(application).peopleDao
        val personId: Long = args.personId

        val viewModelFactory = PersonDetailsViewModelFactory(application,dataSource,personId)
        val viewModel : PersonDetailsViewModel by viewModels{viewModelFactory}

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }
}