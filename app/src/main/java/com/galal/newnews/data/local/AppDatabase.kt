package com.galal.newnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.galal.newnews.domain.entities.Article

@Database(entities = [SavedArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedArticleDao(): SavedArticleDao
}

fun Article.toEntity(): SavedArticleEntity {
    return SavedArticleEntity(
        url ?: "",
        author,
        title,
        description,
        urlToImage,
        publishedAt
    )
}

fun SavedArticleEntity.toDomain(): Article {
    return Article(
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt
    )
}