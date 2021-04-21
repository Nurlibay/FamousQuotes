package com.example.famousquotes._room_dabase.database_class

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.famousquotes._room_dabase.dao.CitataDao
import com.example.famousquotes._room_dabase.entityes.Authors
import com.example.famousquotes._room_dabase.entityes.Citata
import com.example.famousquotes._room_dabase.entityes.Themes

@Database(entities = [Themes::class, Authors::class, Citata::class], version = 1)
abstract class CitataDatabase: RoomDatabase() {

    companion object {
        lateinit var INSTANCE: CitataDatabase
        fun getInstance(context: Context) : CitataDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context, CitataDatabase::class.java,
                    "FamousPeopleQuotes.db")
                    .createFromAsset("FamousPeopleQuotes.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): CitataDao
}