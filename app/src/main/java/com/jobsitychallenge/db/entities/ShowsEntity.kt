package com.jobsitychallenge.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowsEntity(
    @ColumnInfo(name = "show_id") val showId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "summary") val summary: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}