package org.android.ticco.presentation.home

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

    var ticketCategory = MutableLiveData(ALL)
    var ticketSort = MutableLiveData(LATEST)

    fun setCategory(view: View) {
        ticketCategory.value = when (view.id) {
            R.id.cl_all -> ALL
            R.id.cl_musical -> MUSICAL
            R.id.cl_theater -> THEATER
            R.id.cl_movie -> MOVIE
            R.id.cl_exhibition -> EXHIBITION
            R.id.cl_concert -> CONCERT
            R.id.cl_festival -> FESTIVAL
            else -> ALL
        }
    }

    fun setSort(view: View) {
        ticketSort.value = when (view.id) {
            R.id.cl_latest -> LATEST
            R.id.cl_past -> OLDEST
            R.id.cl_high_score -> HIGH_SCORE
            R.id.cl_low_score -> LOW_SCORE
            else -> LATEST
        }
    }

    fun requestTickets(): Flow<PagingData<Ticket>> {
        return ticketsUseCase.getTickets(
            ticketCategory.value,
            1,
            when(ticketSort.value) {
                LATEST -> arrayOf(CREATED_AT, DESC)
                OLDEST ->arrayOf(CREATED_AT, ASC)
                HIGH_SCORE ->arrayOf(RATING, DESC)
                LOW_SCORE ->arrayOf(RATING, ASC)
                else -> emptyArray()
            }
        )
            .cachedIn(viewModelScope)
    }

    fun requestDeleteTicket(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteTicketUseCase.deleteTicket(id)
            }.onSuccess {
                requestTickets()
            }
        }
    }

    companion object {
        const val ASC = "ASC"
        const val DESC = "DESC"
        const val CREATED_AT = "createdAt"
        const val RATING = "rating"
        const val HIGH_SCORE = "별점 높은순"
        const val LOW_SCORE="별점 낮은순"
        const val LATEST = "최신순"
        const val OLDEST = "과거순"
        const val ALL = ""
        const val MUSICAL = "MUSICAL"
        const val THEATER = "THEATER"
        const val MOVIE = "MOVIE"
        const val EXHIBITION = "EXHIBITION"
        const val CONCERT = "CONCERT"
        const val FESTIVAL = "FESTIVAL"
    }

}