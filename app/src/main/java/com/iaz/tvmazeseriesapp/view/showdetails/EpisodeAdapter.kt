package com.iaz.tvmazeseriesapp.view.showdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iaz.tvmazeseriesapp.databinding.ItemEpisodeBinding
import com.iaz.tvmazeseriesapp.repository.model.Episode

class EpisodeAdapter(
    private val onItemClick: ((Episode) -> Unit)
) :
    ListAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding,
        private val onItemClick: (Episode) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.episode = episode

            this.itemView.setOnClickListener {
                onItemClick(episode)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }
}

