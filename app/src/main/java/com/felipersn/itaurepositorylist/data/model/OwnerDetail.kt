package com.felipersn.itaurepositorylist.data.model

import com.google.gson.annotations.SerializedName

data class OwnerDetail(
    val login: String? = null,
    val id: Long? = null,

    @SerializedName("avatar_url")
    val avatarURL: String? = null
)