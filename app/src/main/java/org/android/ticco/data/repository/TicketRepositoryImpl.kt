package org.android.ticco.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.android.ticco.domain.datasource.remote.TicketRemoteDataSource
import org.android.ticco.domain.model.Ticket
import org.android.ticco.domain.repository.TicketRepository
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val dataSource: TicketRemoteDataSource
) : TicketRepository {

    override  fun getPagingTickets(
        category: String?,
        size: Int,
        sort: Array<String>
    ): Flow<PagingData<Ticket>> =
        dataSource.getTicketsList(category, size, sort).map { it.map { value -> value.toTicket() }}


}