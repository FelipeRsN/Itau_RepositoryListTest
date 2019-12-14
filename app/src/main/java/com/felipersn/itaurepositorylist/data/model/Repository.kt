package com.felipersn.itaurepositorylist.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Long? = null,

    val name: String? = null,

    @SerializedName("full_name")
    val fullName: String? = null,

    val owner: OwnerDetail? = null,

    val description: String? = null,

    val url: String? = null,

    @SerializedName("forks_count")
    val forksCount: Long? = null,

    @SerializedName("stargazers_count")
    val stargazersCount: Long? = null,

    val forks: Long? = null
)