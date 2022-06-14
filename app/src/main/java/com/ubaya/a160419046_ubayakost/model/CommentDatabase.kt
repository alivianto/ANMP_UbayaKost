package com.ubaya.a160419046_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Comment::class), version = 1)
abstract class CommentDatabase:RoomDatabase() {
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var instance: RoomDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CommentDatabase::class.java,
                "kostdb"
            ).build()

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}