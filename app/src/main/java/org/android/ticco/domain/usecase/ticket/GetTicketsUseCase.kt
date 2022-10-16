package org.android.ticco.domain.usecase.ticket

import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.android.ticco.domain.model.Ticket
import org.android.ticco.domain.repository.TicketRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTicketsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {

    fun getTickets(
        category: String?,
        size: Int,
        sort: Array<String>
    ): Flow<PagingData<Ticket>> = ticketRepository.getPagingTickets(category, size, sort)
}