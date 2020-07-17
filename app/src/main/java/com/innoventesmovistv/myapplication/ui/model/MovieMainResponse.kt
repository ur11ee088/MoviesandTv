package com.innoventesmovistv.myapplication.ui.model

import java.io.Serializable

data class MovieMainResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
):Serializable