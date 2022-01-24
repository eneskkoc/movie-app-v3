package com.eneskkoc.android.data.model.nowmodel

data class Now(
    val dates: Dates?,
    val page: Int?,
    val results: List<NowResult>?,
    val total_pages: Int?,
    val total_results: Int?
)