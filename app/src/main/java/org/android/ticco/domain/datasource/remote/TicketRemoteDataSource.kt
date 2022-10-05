package org.android.ticco.domain.datasource.remote

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.ticket.model.TicketDto

interface TicketRemoteDataSource {

    fun getTicketsList(category:String?, size: Int, sort:Array<String>): Flow<PagingData<TicketDto>>

    suspend fun deleteTicket(ticketId:Int):BasicResponse

}