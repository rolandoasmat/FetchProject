package com.example.fetchproject.data

import com.google.gson.annotations.SerializedName

data class ItemApiResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("listId")
    val listId: Int?,
    @SerializedName("name")
    val name: String?,
)