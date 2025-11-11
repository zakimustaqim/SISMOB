package com.example.sismob.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HeaderData(
    val status: String?,
    val message: String? = null,
    val dept: String? = null,
    val datas: UserDatas? = null,
    val access_token: String?= null,
    val token_type: String? = null
)
