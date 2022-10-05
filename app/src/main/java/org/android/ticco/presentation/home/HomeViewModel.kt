package org.android.ticco.presentation.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.android.ticco.R
import org.android.ticco.domain.model.Ticket
import org.android.ticco.domain.usecase.ticket.DeleteTicketUseCase
import org.android.ticco.domain.usecase.ticket.GetTicketsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ticketsUseCase: GetTicketsUseCase,
    private val deleteTicketUseCase: DeleteTicketUseCase
) : ViewModel() {

    var isEmptyTicket = MutableLiveData<Boolean>()

    var ticketCategory = MutableLiveData("")

    fun setCategory(view: View) {
        ticketCategory.value = when(view.id) {
            R.id.cl_all -> ""
            R.id.cl_musical -> "musical"
            R.id.cl_theater -> "theater"
            R.id.cl_movie ->"movie"
            R.id.cl_exhibition -> "exhibition"
            R.id.cl_concert -> "concert"
            R.id.cl_festival -> "festival"
            else -> ""
        }
    }

    fun requestTickets(): Flow<PagingData<Ticket>> {
        Log.d("TAG", "asdfasdfasssss : ${ticketCategory.value?.uppercase()}")
        return ticketsUseCase.getTickets(ticketCategory.value?.uppercase(), 1, arrayOf("createdAt", DESC))
            .cachedIn(viewModelScope)
    }

    fun requestDeleteTicket(id:Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteTicketUseCase.deleteTicket(id)
            }
        }
    }



    companion object {
        const val ASC = "ASC"
        const val DESC = "DESC"
    }

}