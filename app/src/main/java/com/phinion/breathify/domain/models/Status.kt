package com.phinion.breathify.domain.models

data class Status(
    val code: Int,
    val err: String,
    val msg: String,
    val retry: Boolean
)