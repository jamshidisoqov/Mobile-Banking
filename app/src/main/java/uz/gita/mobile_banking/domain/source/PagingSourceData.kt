package uz.gita.mobile_banking.domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.mobile_banking.data.remote.api.TransferApi
import uz.gita.mobile_banking.data.remote.response.transfer.TransferData
import uz.gita.mobile_banking.utils.ResultData
import uz.gita.mobile_banking.utils.func
import javax.inject.Inject

// Created by Jamshid Isoqov on 1/3/2023
class PagingSourceData @Inject constructor(
    private val api: TransferApi,
    private val gson: Gson
) : PagingSource<Int, TransferData>() {

    val pageData = MutableSharedFlow<ResultData<TransferData>>()

    private var pageSize = 10

    override fun getRefreshKey(state: PagingState<Int, TransferData>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransferData> {
        val page = params.key ?: 1
        pageSize = params.loadSize
        val response = api.getTransfers(page, pageSize)
        var transfers = emptyList<TransferData>()
        response.func(gson)
            .onSuccess {
                transfers = it.transfers
            }.onMessage {
                return LoadResult.Error(Throwable(it))
            }.onError {
                return LoadResult.Error(it)
            }
        val nextKey = if (transfers.size < pageSize) null else page + 1
        val prevKey = if (page == 1) null else page - 1
        return LoadResult.Page(transfers, prevKey, nextKey)
    }
}