package com.phinion.breathify.domain.models

data class BreathingToolData(
    val code: String,
    val id: String,
    val prc: List<Prc>,
    val type: Int,
    val uid: String,
    val wea: Boolean
)