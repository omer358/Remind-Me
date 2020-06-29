package com.example.remindme.screens.people

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.remindme.R
import com.example.remindme.convertDateToPassedTime
import com.example.remindme.database.People
import com.example.remindme.databinding.PeopleListItemBinding

class PeopleAdapter:ListAdapter<People,PeopleAdapter.ViewHolder>(PeopleDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(
         val binding: PeopleListItemBinding
    ): RecyclerView.ViewHolder(binding.root
    ){
        fun bind(
            item: People
        ) {
            binding.personNameItem.text = item.firstName + " " + item.secondName
            binding.imageItem.setImageResource(
                when (item.gender) {
                    "Male" -> R.drawable.ic_baseline_emoji_emotions_30
                    "Female" -> R.drawable.ic_baseline_sentiment_very_satisfied_30
                    else -> R.drawable.ic_baseline_person_outline_24
                }
            )
            binding.timeMeetingItem.text = convertDateToPassedTime(item.time)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PeopleListItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class PeopleDiffCallback: DiffUtil.ItemCallback<People>(){
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
        return oldItem == newItem
    }

}
