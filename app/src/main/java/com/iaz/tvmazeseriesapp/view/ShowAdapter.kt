package com.iaz.tvmazeseriesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemShowBinding
import com.iaz.tvmazeseriesapp.repository.model.Show

class ShowAdapter(private val onItemClick: ((Show) -> Unit)) :
    PagingDataAdapter<Show, ShowAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemShowBinding,
        private val onItemClick: (Show) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: Show?) {
            show?.let {
                binding.show = show
                itemView.setOnClickListener { onItemClick(show) }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }
}