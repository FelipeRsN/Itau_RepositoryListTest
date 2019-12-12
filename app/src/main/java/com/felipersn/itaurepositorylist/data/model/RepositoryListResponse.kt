package com.felipersn.itaurepositorylist.data.model

import com.google.gson.annotations.SerializedName


data class RepositoryListResponse(
    @SerializedName("total_count")
    val totalCount: Long? = null,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    val items: List<Repository>? = null
)

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

data class OwnerDetail(
    val login: String? = null,
    val id: Long? = null,

    @SerializedName("avatar_url")
    val avatarURL: String? = null,

    @SerializedName("gravatar_id")
    val gravatarID: String? = null,

    val url: String? = null,

    @SerializedName("repos_url")
    val reposURL: String? = null,

    @SerializedName("events_url")
    val eventsURL: String? = null,

    @SerializedName("received_events_url")
    val receivedEventsURL: String? = null,

    @SerializedName("site_admin")
    val siteAdmin: Boolean? = null
)

