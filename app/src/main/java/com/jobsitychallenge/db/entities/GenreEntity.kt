package com.jobsitychallenge.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @ColumnInfo(name = "showId") val showId: Int,
    @ColumnInfo(name = "name") val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}