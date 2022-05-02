package com.jobsitychallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jobsitychallenge.db.dao.ChallengeDao
import com.jobsitychallenge.db.entities.*

@Database(entities = [
    ShowsEntity::class,
    ImageEntity::class,
    ScheduleEntity::class,
    DaysEntity::class,
    GenreEntity::class
], version = 1)
abstract class ChallengeDB : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
}
