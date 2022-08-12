package org.android.ticco.data.datasource.remote.ticket.model

import com.google.gson.annotations.SerializedName
import org.android.ticco.domain.model.Ticket

data class TicketDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("theme")
    val theme: String,
    @SerializedName("ticketId")
    val id: Int,
    @SerializedName("ticketImageUrl")
    val image: String,
    @SerializedName("title")
    val title: String
) {
    fun toTicket(): Ticket = Ticket(
        id = this.id,
        title = this.title,
        category = this.category,
        date = this.date,
        content = this.content,
        rating = this.rating,
        image = this.image,
        theme = this.theme
    )
}