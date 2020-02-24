package com.uhc.shapescreator.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhc.shapescreator.R
import com.uhc.shapescreator.databinding.StatsListItemBinding

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    private val stats = mutableListOf<StatsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.stats_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = stats.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stats[position])
    }

    fun notifyChanged(stats: List<StatsModel>) {
        this.stats.clear()
        this.stats.addAll(stats)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: StatsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stats: StatsModel) {
            binding.stats = stats
            binding.executePendingBindings()
        }
    }
}