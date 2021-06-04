package com.bangkit.submissionmade1.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var newsId: Int = 0,

    @ColumnInfo(name = "author")
    var author: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "urlToImage")
    var urlImage: String?,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name = "content")
    var content: String?,

    @ColumnInfo(name = "categoryContent")
    var category: String?,

    @ColumnInfo(name = "isFav")
    var isFav: Boolean = false

)
