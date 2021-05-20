package uz.texnopos.famousquotes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citata")
data class Citata(
        @PrimaryKey val id: Int,
        @ColumnInfo(name="citata_text") var text: String,
        @ColumnInfo(name="theme_id") val themeId: Int,
        @ColumnInfo(name="author_id") val authorId: Int,

        @ColumnInfo(name="isFavorite") var isFavorite: Int
)