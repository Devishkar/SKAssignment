package com.posist.sk.data

import com.google.gson.annotations.SerializedName


data class SKData (
        @SerializedName("cards")
        val dataItems: List<SKDataItems>
        )
data class SKDataItems(
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("published_date")
        val publishedAt: String,
        @SerializedName("title")
        val title: String
)