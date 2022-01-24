package com.eneskkoc.android.data.model.comingmodel

data class Coming(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)