package com.example.sismob.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MsgInfo(
    @SerialName("Msg") val msgInfo: String
)
