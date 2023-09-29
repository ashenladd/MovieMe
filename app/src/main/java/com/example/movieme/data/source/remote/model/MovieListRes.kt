package com.example.movieme.data.source.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieListRes(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val total_pages: Int? = null,
    @SerializedName("total_results")
    val total_results: Int? = null,
    @SerializedName("results")
    val results: List<MovieResultRes>? = null,
)