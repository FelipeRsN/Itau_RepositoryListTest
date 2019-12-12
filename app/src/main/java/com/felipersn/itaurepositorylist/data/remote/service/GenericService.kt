package com.felipersn.itaurepositorylist.data.remote.service

import com.felipersn.itaurepositorylist.data.model.RepositoryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenericService {

    @GET("search/repositories")
    suspend fun getListOfRepository(@Query("q") language: String, @Query("sort") sortType: String, @Query("page") page: Int): RepositoryListResponse?

}