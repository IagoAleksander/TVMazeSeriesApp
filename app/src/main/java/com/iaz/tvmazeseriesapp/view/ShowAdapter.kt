package com.iaz.tvmazeseriesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemShowBinding
import com.iaz.tvmazeseriesapp.repository.model.Show

class ShowAdapter(
    private val onItemClick: ((Show) -> Unit)
) :
    ListAdapter<Show, ShowAdapter.ShowViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding =
            ItemShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ShowViewHolder(
        private val binding: ItemShowBinding,
        private val onItemClick: (Show) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: Show) {
            binding.show = show

            this.itemView.setOnClickListener {
                onItemClick(show)
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

