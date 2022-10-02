package org.android.ticco.data.datasource.remote.ticket.model

data class TicketResponse(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val contents: List<TicketDto>,
        val lastPage: Int,
        val nextPage: Int
    )
}