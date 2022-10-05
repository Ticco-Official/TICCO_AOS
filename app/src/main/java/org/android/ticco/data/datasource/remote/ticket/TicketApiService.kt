package org.android.ticco.data.datasource.remote.ticket

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.ticket.model.TicketResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TicketApiService {

    @GET("v1/ticket")
    suspend fun getTickets(
        @Query("category") category: String?,
        @Query("page") page:Int=0,
        @Query("size") size:Int=1,
        @Query("sort") sort: String
    ): TicketResponse

    @DELETE("v1/ticket/{ticketId}")
    suspend fun deleteTicket(
        @Path("ticketId") ticketId: Int
    ): BasicResponse
}