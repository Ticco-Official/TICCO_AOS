package org.android.ticco.domain.model

data class Ticket(
    val id: Int,
    val title: String,
    val category: String,
    val date: String,
    val content: String,
    val rating: Int,
    val image: String,
    val theme: String
)