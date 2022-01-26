package com.example.movieapp.ui.main

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.room.HistoryDAO
import com.example.movieapp.data.room.HistoryDataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDAO(): HistoryDAO {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        appInstance?.let {
                            db = Room.databaseBuilder(
                                it.applicationContext,
                                HistoryDataBase::class.java,
                                DB_NAME
                            ).build()
                        } ?: throw Exception("FATAL EXCEPTION")
                    }
                }
            }
            return db!!.historyDAO()
        }
    }
}