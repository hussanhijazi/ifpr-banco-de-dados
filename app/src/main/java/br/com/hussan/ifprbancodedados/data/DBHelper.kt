package br.com.hussan.ifprbancodedados.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class DBHelper private constructor(
    context: Context,
    name: String,
    factory: CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DROP)
        sqLiteDatabase.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        onCreate(sqLiteDatabase)
    }

    companion object {
        const val DB_NAME = "seriesdb"
        const val DB_VERSION = 1
        private const val SQL_DROP = "DROP TABLE IF EXISTS " + SeriesContract.TABLE_NAME
        private val SQL_CREATE = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT NOT NULL, %s DOUBLE NOT NULL)",
            SeriesContract.TABLE_NAME,
            SeriesContract.Columns._ID,
            SeriesContract.Columns.NAME,
            SeriesContract.Columns.STREAMING
        )
        private var instance: DBHelper? = null

        //singleton
        @JvmStatic
        fun getInstance(context: Context): DBHelper? {
            if (instance == null) {
                instance = DBHelper(context, DB_NAME, null, DB_VERSION)
            }
            return instance
        }
    }
}