package com.galal.newnews.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: SavedArticleEntity)

    @Delete
    suspend fun deleteArticle(article: SavedArticleEntity)

    @Query("SELECT * FROM saved_articles")
    suspend fun getAllArticles(): List<SavedArticleEntity>
}