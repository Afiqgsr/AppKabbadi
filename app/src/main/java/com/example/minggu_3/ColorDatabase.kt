package com.example.minggu_3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Color::class], version = 1)
abstract class ColorDatabase: RoomDatabase() {

    abstract fun ColorDao(): ColorDao

    companion object{
        private var INSTANCE: ColorDatabase? = null

        fun getInstance(context: Context):ColorDatabase{
            return INSTANCE?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ColorDatabase::class.java, "color_database"
                ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE) // Gunakan TRUNCATE untuk hanya menyimpan 1 file database
                    .build()
            }
        }
    }
}