package com.phinion.breathify.domain.models

data class BreathingProgressData(
    val ach: Int,
    val date: Int,
    val id: String,
    val rcm: Int,
    val tgt: Int,
    val uid: String
)