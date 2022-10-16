package org.android.ticco.domain.usecase.ticket

import org.android.ticco.domain.repository.TicketRepository
import javax.inject.Inject

class DeleteTicketUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {

    suspend fun deleteTicket(ticketId: Int):Boolean =
        ticketRepository.deleteTicket(ticketId).success
}