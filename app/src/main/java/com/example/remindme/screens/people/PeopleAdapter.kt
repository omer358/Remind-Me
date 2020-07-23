package com.example.remindme.screens.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.remindme.R
import com.example.remindme.convertDateToPassedTime
import com.example.remindme.database.People
import com.example.remindme.databinding.PeopleListItemBinding

class PeopleAdapter(private val personClickListener: PersonClickListener) : ListAdapter<People, PeopleAdapter.ViewHolder>(PeopleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,personClickListener)
    }

    class ViewHolder private constructor(
        val binding: PeopleListItemBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(
            item: People,
            personClickListener: PersonClickListener
        ) {
            binding.personNameItem.text = item.firstName + " " + item.secondName
            binding.imageItem.setImageResource(
                when (item.gender) {
                    "male" -> R.drawable.ic_avatar_1
                    "female" -> R.drawable.ic_avatar_16
                    else -> R.drawable.ic_baseline_person_outline_24
                }
            )
            binding.registerationTimeItem.text = convertDateToPassedTime(item.registrationTime)
            binding.parent.setOnClickListener {
                personClickListener.onclick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PeopleListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PeopleDiffCallback : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem == newItem
    }
}

class PersonClickListener(val clickListener: (personId: Long) ->Unit){
    fun onclick(people: People) = clickListener(people.id)
}
