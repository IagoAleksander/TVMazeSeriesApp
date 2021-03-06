package com.iaz.tvmazeseriesapp.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemGridBinding
import com.iaz.tvmazeseriesapp.repository.model.Show

class ShowPaginatedAdapter(private val onItemClick: ((Show) -> Unit)) :
    PagingDataAdapter<Show, ShowPaginatedAdapter.ShowViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ShowViewHolder(
        private val binding: ItemGridBinding,
        private val onItemClick: (Show) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: Show?) {
            show?.let {
                binding.item = show
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