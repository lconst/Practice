package com.example.practice.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Category(
    @SerialName("id")
    val id: Int,
    @SerialName("imageResourceName")
    val imageResourceName: String,
    @SerialName("name")
    val name: String,
    var isEnabled: Boolean = true
) : Parcelable
