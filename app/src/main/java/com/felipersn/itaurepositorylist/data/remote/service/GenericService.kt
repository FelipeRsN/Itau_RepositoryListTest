package com.felipersn.itaurepositorylist.data.remote.service

import com.felipersn.itaurepositorylist.data.model.PullRequestsResponse
import com.felipersn.itaurepositorylist.data.model.RepositoryListResponse
import com.google.gson.JsonArray
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenericService {

    @GET("search/repositories")
    suspend fun getListOfRepository(@Query("q") language: String, @Query("sort") sortType: String, @Query("page") page: Int): RepositoryListResponse?

    @GET("repos/{owner_login}/{repository_name}/pulls")
    suspend fun getListOfRepository(@Path(value = "owner_login") ownerLogin: String, @Path(value = "repository_name") repositoryName: String): ArrayList<PullRequestsResponse?>?

}