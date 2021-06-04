package com.bangkit.submissionmade1.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val newsId: Int,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlImage: String,
    val publishedAt: String,
    val content: String,
    val category: String,
    val isFav: Boolean
) : Parcelable