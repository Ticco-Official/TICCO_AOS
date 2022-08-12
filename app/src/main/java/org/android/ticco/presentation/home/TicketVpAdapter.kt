package org.android.ticco.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.ticco.databinding.ItemTicketBinding

class TicketVpAdapter :
    RecyclerView.Adapter<TicketVpAdapter.PagerViewHolder>() {

    var item = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item[position])
    }

    inner class PagerViewHolder(private val binding: ItemTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.ivTicket.setImageResource(item)
        }
    }
}