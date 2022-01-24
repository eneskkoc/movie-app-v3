package com.eneskkoc.android.data.model.searchmodel

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average:  Double?,
    val vote_count: Int?
): SearchSuggestion {
    override fun getBody(): String? {
      return title
    }
}