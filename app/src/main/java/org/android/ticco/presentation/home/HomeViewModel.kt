package org.android.ticco.presentation.home

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

    val category = MutableLiveData<String>()

    fun requestTickets(): Flow<PagingData<Ticket>> =
        ticketsUserCase.getTickets(category.value, 1, arrayOf("createdAt", DESC))
            .cachedIn(viewModelScope)


    companion object {
        const val ASC = "ASC"
        const val DESC = "DESC"
    }

}