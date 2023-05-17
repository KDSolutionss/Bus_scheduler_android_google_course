package com.example.busschedule

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule
import com.example.busschedule.databinding.BusStopItemBinding
import java.util.*

class BusStopAdapter(private val onItemClicked: (schedule) -> Unit) : ListAdapter<schedule,BusStopAdapter.ViewHolder>(DiffCallback)
{
    class ViewHolder(private var binding:BusStopItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(schedule: schedule)
        {
            binding.stopNameTextView.text=schedule.stopName
            binding.arrivalTimeTextView.text= SimpleDateFormat(
                "h:mm a").format(
                Date(schedule.arrivalTime.toLong() * 1000)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder=ViewHolder(BusStopItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        viewHolder.itemView.setOnClickListener {
            onItemClicked(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        private val DiffCallback= object :DiffUtil.ItemCallback<schedule>()
        {
            override fun areItemsTheSame(oldItem: schedule, newItem: schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: schedule, newItem: schedule): Boolean {
                return oldItem == newItem
            }

        }
    }


}