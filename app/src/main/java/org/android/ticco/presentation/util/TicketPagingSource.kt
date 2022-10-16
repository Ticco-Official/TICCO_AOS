package org.android.ticco.presentation.util

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import org.android.ticco.data.datasource.remote.ticket.TicketApiService
import org.android.ticco.data.datasource.remote.ticket.model.TicketDto

class TicketPagingSource(
    private val category: String?,
    private val sort: Array<String>,
    private val service: TicketApiService
) : PagingSource<Int, TicketDto>() {


    override fun getRefreshKey(state: PagingState<Int, TicketDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TicketDto> {
        return try {
            val next = params.key ?: 0
            val response = service.getTickets(category, next, 1, "${sort[0]},${sort[1]}")
            if (response.success && response.data.contents.isNotEmpty()) {
                LoadResult.Page(
                    data = response.data.contents,
                    prevKey = null,
                    nextKey = if (response.data.nextPage == -1) null else next + 1
                )
            } else {
                LoadResult.Page(
                    data = response.data.contents,
                    prevKey = null,
                    nextKey = null
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}