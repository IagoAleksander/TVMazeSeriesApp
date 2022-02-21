package com.iaz.tvmazeseriesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemGridBinding
import com.iaz.tvmazeseriesapp.repository.model.GridItem

class GridAdapter(
    private val onItemClick: ((GridItem) -> Unit)
) :
    ListAdapter<GridItem, GridAdapter.GridItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val binding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GridItemViewHolder(
        private val binding: ItemGridBinding,
        private val onItemClick: (GridItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GridItem) {
            binding.item = item

            this.itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GridItem>() {
        override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
            return oldItem == newItem
        }
    }
}

