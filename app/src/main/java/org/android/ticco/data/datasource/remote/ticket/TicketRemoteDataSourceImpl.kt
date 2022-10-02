package org.android.ticco.data.datasource.remote.ticket

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.ticco.data.datasource.remote.ticket.model.TicketDto
import org.android.ticco.domain.datasource.remote.TicketRemoteDataSource
import org.android.ticco.presentation.util.TicketPagingSource
import javax.inject.Inject

class TicketRemoteDataSourceImpl @Inject constructor(
    private val service: TicketApiService
) : TicketRemoteDataSource {

    override fun getTicketsList(
        category: String?,
        size: Int,
        sort: Array<String>
    ): Flow<PagingData<TicketDto>> =
        Pager(
            config = PagingConfig(pageSize = size, enablePlaceholders = false),
            pagingSourceFactory = {
                TicketPagingSource(category = category, sort = sort, service)
            }
        ).flow

}