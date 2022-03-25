package com.jva.data.model

data class TicketResponse(
    val ticket: Ticket,
    val message: String,
    val status: Int
)
data class Ticket(
    val msg: String,
    val srno: Boolean
)