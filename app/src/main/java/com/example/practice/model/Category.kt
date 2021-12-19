package com.example.practice.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id")
    val id: Int,
    @SerialName("imageResourceName")
    val imageResourceName: String,
    @SerialName("name")
    val name: String
) {
    var isEnabled = true
}
