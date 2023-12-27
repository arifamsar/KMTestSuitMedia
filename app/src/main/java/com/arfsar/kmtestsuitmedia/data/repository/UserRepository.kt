package com.arfsar.kmtestsuitmedia.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.arfsar.kmtestsuitmedia.data.Result
import com.arfsar.kmtestsuitmedia.data.UserPagingSource
import com.arfsar.kmtestsuitmedia.data.retrofit.api.ApiService
import com.arfsar.kmtestsuitmedia.data.retrofit.response.DataItem

class UserRepository private constructor(
    private val apiService: ApiService
) {
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    fun getSingleUser(id: Int) = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.detailUser(id)
            emit(Result.Success(client))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }
    }
}