package dev.orgsusu.common.response

sealed interface BaseResponse {
    val code: String
    val status: Int
}
