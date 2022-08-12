package org.android.ticco.presentation.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.android.ticco.data.datasource.remote.ticket.TicketApiService
import org.android.ticco.data.datasource.remote.ticket.model.TicketDto

class TicketPagingSource(
    private val category: String?,
    private val size: Int = 3,
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
            val response =
                service.getTickets(category, next, size, "${sort[0]},${sort[1]}").data.contents
            LoadResult.Page(
                data = response,
                prevKey = if (next == -1) null else next - 1,
                nextKey = if (next == -1) null else next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}