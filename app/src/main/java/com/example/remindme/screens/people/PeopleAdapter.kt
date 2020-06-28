package com.example.remindme.screens.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remindme.R
import com.example.remindme.database.People

class PeopleAdapter:RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<People>()
    set(value) {
        field =value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

}

class ViewHolder private constructor( itemView: View): RecyclerView.ViewHolder(itemView){
    val personName: TextView = itemView.findViewById(R.id.person_name_item)
    val meetingTime: TextView = itemView.findViewById(R.id.time_meeting_item)
    val genderImage: ImageView = itemView.findViewById(R.id.image_item)

     fun bind(
    item: People
    ) {
        personName.text = item.firstName
        genderImage.setImageResource(
            when (item.gender) {
                "Male" -> R.drawable.ic_baseline_emoji_emotions_30
                "Female" -> R.drawable.ic_baseline_sentiment_very_satisfied_30
                else -> R.drawable.ic_baseline_person_outline_24
            }
        )
        meetingTime.text = item.time
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.people_list_item, parent, false)
            return ViewHolder(view)
        }
    }
}
