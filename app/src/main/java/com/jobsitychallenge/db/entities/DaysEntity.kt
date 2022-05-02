package com.jobsitychallenge.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DaysEntity(
    @ColumnInfo(name = "showId") val showId: Int,
    @ColumnInfo(name = "day") val day: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}