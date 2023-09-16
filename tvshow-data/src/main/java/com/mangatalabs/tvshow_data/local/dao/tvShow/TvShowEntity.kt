package com.mangata.tvshow_data.local.dao.tvShow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mangatalabs.tvshow_data.local.database.SofaTimeDatabase.Companion.DATABASE_TV_SHOW_TABLE

@Entity(tableName = DATABASE_TV_SHOW_TABLE)
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
)
