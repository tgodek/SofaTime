package com.mangatalabs.tvshow_data.local.dao.tvShow

import androidx.room.*
import com.mangata.tvshow_data.local.dao.tvShow.TvShowEntity

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows_table")
    suspend fun getAllTrackedTvShows(): List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchList(tvShow: TvShowEntity)

    @Query("DELETE FROM tv_shows_table WHERE id = :id")
    suspend fun deleteTrackedTvShow(id: Int)
}