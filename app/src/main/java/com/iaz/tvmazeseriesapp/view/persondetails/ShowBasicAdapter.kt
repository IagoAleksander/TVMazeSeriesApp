package com.iaz.tvmazeseriesapp.view.persondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemShowBasicBinding
import com.iaz.tvmazeseriesapp.repository.model.ShowBasic

class ShowBasicAdapter(
    private val onItemClick: ((ShowBasic) -> Unit)
) :
    ListAdapter<ShowBasic, ShowBasicAdapter.ShowBasicViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowBasicViewHolder {
        val binding =
            ItemShowBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowBasicViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ShowBasicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ShowBasicViewHolder(
        private val binding: ItemShowBasicBinding,
        private val onItemClick: (ShowBasic) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(showBasic: ShowBasic) {
            binding.show = showBasic

            this.itemView.setOnClickListener {
                onItemClick(showBasic)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ShowBasic>() {
        override fun areItemsTheSame(oldItem: ShowBasic, newItem: ShowBasic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShowBasic, newItem: ShowBasic): Boolean {
            return oldItem == newItem
        }
    }
}

