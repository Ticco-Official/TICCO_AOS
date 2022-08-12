package org.android.ticco.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.android.ticco.domain.model.Ticket
import org.android.ticco.domain.usecase.ticket.GetTicketsUserCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ticketsUserCase: GetTicketsUserCase
) : ViewModel() {

    private val _ticketData = MutableLiveData<ArrayList<Int>>()
    val ticketData: LiveData<ArrayList<Int>>
        get() = _ticketData

    val category = MutableLiveData<String>()
    var createdAt = MutableLiveData<String>("DESC")

    fun requestTickets(): Flow<PagingData<Ticket>> =
        ticketsUserCase.getTickets(category.value, 1, arrayOf("createdAt", DESC))
            .cachedIn(viewModelScope)


    /*fun requestTickets() {
        val ticket: Flow<PagingData<Ticket>> =
            ticketsUserCase(category.value!!, 1, arrayOf("createdAt",DESC))

        *//*viewModelScope.launch {
            kotlin.runCatching {
                ticketsUserCase("",1, arrayOf("createdAt",DESC))
            }
        }*//*
        //_ticketData.value = arrayListOf(R.drawable.img_welcome, R.drawable.img_welcome, R.drawable.img_welcome, R.drawable.img_welcome)
    }*/

    companion object {
        const val ASC = "ASC"
        const val DESC = "DESC"
    }


}