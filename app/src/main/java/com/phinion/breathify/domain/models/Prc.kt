package com.phinion.breathify.domain.models

data class Prc(
    val code: String,
    val dsc: String,
    val isMultiSel: Boolean,
    val ttl: String,
    val type: Int,
    val values: List<Value>
)