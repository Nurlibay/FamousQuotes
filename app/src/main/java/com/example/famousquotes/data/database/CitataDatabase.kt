package com.example.famousquotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.entityes.Author
import com.example.famousquotes.data.entityes.Citata
import com.example.famousquotes.data.entityes.Theme

@Database(entities = [Theme::class, Author::class, Citata::class], version = 1)
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