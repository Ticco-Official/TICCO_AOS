package org.android.ticco.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.ticco.domain.model.Ticket

interface TicketRepository {

     fun getPagingTickets(
        category: String?,
        size: Int,
        sort: Array<String>
    ): Flow<PagingData<Ticket>>
}