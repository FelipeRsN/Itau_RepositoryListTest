package com.felipersn.itaurepositorylist.data.model

import com.google.gson.annotations.SerializedName

data class PullRequestsResponse(
    val id: Long? = null,
    val state: String? = null,
    val locked: Boolean? = null,
    val title: String? = null,
    val user: OwnerDetail? = null,
    val body: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null
)
