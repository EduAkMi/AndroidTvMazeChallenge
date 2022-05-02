package com.jobsitychallenge.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jobsitychallenge.db.entities.*

@Dao
interface ChallengeDao {
    @Insert
    fun addShow(show: ShowsEntity)
    @Insert
    fun addImage(image: ImageEntity)
    @Insert
    fun addSchedule(schedule: ScheduleEntity)
    @Insert
    fun addDay(day: DaysEntity)
    @Insert
    fun addGenre(genre: GenreEntity)


    @Query("SELECT * FROM shows WHERE show_id = :id")
    fun getShow(id: Int): ShowsEntity?

    @Query("SELECT * FROM shows")
    fun getAllShows(): List<ShowsEntity>
    @Query("SELECT * FROM genre WHERE showId = :showId")
    fun getShowGenres(showId: Int): List<GenreEntity>
    @Query("SELECT * FROM image WHERE showId = :showId")
    fun getShowImage(showId: Int): ImageEntity
    @Query("SELECT * FROM schedule WHERE showId = :showId")
    fun getShowSchedule(showId: Int): ScheduleEntity
    @Query("SELECT * FROM days WHERE showId = :showId")
    fun getShowScheduleDays(showId: Int): List<DaysEntity>

    @Query("DELETE FROM shows WHERE show_id = :id")
    fun removeShow(id: Int)
    @Query("DELETE FROM genre WHERE showId = :showId")
    fun removeShowGenres(showId: Int)
    @Query("DELETE FROM image WHERE showId = :showId")
    fun removeShowImage(showId: Int)
    @Query("DELETE FROM schedule WHERE showId = :showId")
    fun removeShowSchedule(showId: Int)
    @Query("DELETE FROM days WHERE showId = :showId")
    fun removeShowScheduleDays(showId: Int)

}
