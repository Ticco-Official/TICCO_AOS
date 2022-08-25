package org.android.ticco.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.android.ticco.databinding.ItemTicketBinding
import org.android.ticco.domain.model.Ticket

class TicketPagingAdapter :
    PagingDataAdapter<Ticket, TicketPagingAdapter.TicketViewHolder>(TicketDiffUtil) {

    class TicketViewHolder(
        private val binding: ItemTicketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: Ticket) {
            binding.ticket = ticket
        }
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder =
        TicketViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        private val TicketDiffUtil = object : DiffUtil.ItemCallback<Ticket>() {
            override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Ticket,
                newItem: Ticket
            ): Boolean =
                oldItem == newItem

        }
    }
}