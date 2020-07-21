package com.innoventesmovistv.myapplication.ui.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieMainResponse(
    @SerializedName("results")
    var results: List<MovieResult>
):Serializable