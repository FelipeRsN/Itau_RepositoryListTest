package com.felipersn.itaurepositorylist.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryListResponse(
    @SerializedName("total_count")
    val totalCount: Long? = null,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    val items: List<Repository>? = null
)


