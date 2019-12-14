package com.felipersn.itaurepositorylist.data.remote.repository

import com.felipersn.itaurepositorylist.data.remote.service.GenericService
import com.felipersn.itaurepositorylist.data.remote.tools.RetrofitBuilder

class GenericRepository(retrofitBuilder: RetrofitBuilder) {
    var client: GenericService = retrofitBuilder.instance.create(GenericService::class.java)

    suspend fun getRepositoryList(page: Int) = client.getListOfRepository("language:Java", "stars", page)

    suspend fun getPullRequests(ownerLogin: String, repositoryName: String) = client.getListOfRepository(ownerLogin, repositoryName)
}