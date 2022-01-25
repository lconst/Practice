package com.example.practice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Category(
    @SerialName("id")
    @PrimaryKey
    val id: Int,
    @SerialName("imageResourceName")
    @ColumnInfo(name = "image_resource_name")
    val imageResourceName: String,
    @SerialName("name")
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_enabled")
    var isEnabled: Boolean = true
)
