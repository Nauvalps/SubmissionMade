package com.bangkit.submissionmade1.core.utils

import com.bangkit.submissionmade1.core.data.source.local.entity.NewsEntity
import com.bangkit.submissionmade1.core.data.source.remote.response.ArticlesItem
import com.bangkit.submissionmade1.core.domain.model.News
import java.util.ArrayList

object DataMapper {

    fun mapResponseToEntitiesHeadline(input: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content,
                category = "headlines",
                isFav = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapResponseToEntitiesHealth(input: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlImage = it.urlToImage,
                    publishedAt = it.publishedAt,
                    content = it.content,
                    category = "health",
                    isFav = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapResponseToEntitiesSports(input: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlImage = it.urlToImage,
                    publishedAt = it.publishedAt,
                    content = it.content,
                    category = "sports",
                    isFav = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>) : List<News> =
        input.map {
            News(
                newsId = it.newsId,
                author = it.author.toString(),
                title = it.title.toString(),
                description = it.description.toString(),
                url = it.url.toString(),
                urlImage = it.urlImage.toString(),
                publishedAt = it.publishedAt.toString(),
                content = it.content.toString(),
                category = it.category.toString(),
                isFav = it.isFav
            )
        }

    fun mapDomainToEntities(input: News) = NewsEntity(
        newsId = input.newsId,
        author = input.author,
        title = input.title,
        description = input.description,
        url = input.url,
        urlImage = input.urlImage,
        publishedAt = input.publishedAt,
        content = input.content,
        category = input.category,
        isFav = input.isFav
    )
}
