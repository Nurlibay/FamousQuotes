package uz.texnopos.famousquotes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
data class Theme(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="theme_name") val themeName: String
)