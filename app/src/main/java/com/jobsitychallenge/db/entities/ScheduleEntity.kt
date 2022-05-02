package com.jobsitychallenge.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @ColumnInfo(name = "showId") val showId: Int,
    @ColumnInfo(name = "time") val time: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}