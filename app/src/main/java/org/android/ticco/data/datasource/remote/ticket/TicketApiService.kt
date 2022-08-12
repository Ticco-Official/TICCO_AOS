package org.android.ticco.data.datasource.remote.ticket

import org.android.ticco.data.datasource.remote.ticket.model.TicketResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketApiService {

    @GET("v1/ticket?category=&page=&size=&sort=")
    suspend fun getTickets(
        @Query("category") category: String?,
        @Query("page") page:Int =0,
        @Query("size") size:Int = 3,
        @Query("sort") sort: String
    ): TicketResponse
}