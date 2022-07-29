package br.com.hussan.ifprbancodedados.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.lang.String
import kotlin.arrayOf

class SerieDAO private constructor(context: Context) {
    private val db: SQLiteDatabase
    fun list(): List<Serie> {
        val columns = arrayOf(
            SeriesContract.Columns._ID,
            SeriesContract.Columns.NAME,
            SeriesContract.Columns.STREAMING
        )
        val produtos: MutableList<Serie> = ArrayList()
        db.query(
            SeriesContract.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            SeriesContract.Columns._ID
        ).use { c ->
            if (c.moveToFirst()) {
                do {
                    val p = fromCursor(c)
                    produtos.add(p)
                } while (c.moveToNext())
            }
            return produtos
        }
    }

    fun save(serie: Serie): Long {
        val values = ContentValues()
        values.put(SeriesContract.Columns.NAME, serie.name)
        values.put(SeriesContract.Columns.STREAMING, serie.streaming)
        return db.insert(SeriesContract.TABLE_NAME, null, values)
    }

    fun update(serie: Serie) {
        val values = ContentValues()
        values.put(SeriesContract.Columns.NAME, serie.name)
        values.put(SeriesContract.Columns.STREAMING, serie.streaming)
        db.update(
            SeriesContract.TABLE_NAME, values, SeriesContract.Columns._ID + " = ?", arrayOf(
                String.valueOf(serie.id)
            )
        )
    }

    fun delete(serie: Serie) {
        db.delete(
            SeriesContract.TABLE_NAME,
            SeriesContract.Columns._ID + " = ?",
            arrayOf(String.valueOf(serie.id))
        )
    }

    companion object {
        private var instance: SerieDAO? = null

        //singleton
        fun getInstance(context: Context): SerieDAO? {
            if (instance == null) {
                instance = SerieDAO(context.applicationContext)
            }
            return instance
        }

        private fun fromCursor(c: Cursor): Serie {
            @SuppressLint("Range") val id = c.getInt(c.getColumnIndex(SeriesContract.Columns._ID))
            @SuppressLint("Range") val name =
                c.getString(c.getColumnIndex(SeriesContract.Columns.NAME))
            @SuppressLint("Range") val streaming =
                c.getString(c.getColumnIndex(SeriesContract.Columns.STREAMING))
            return Serie(id, name, streaming)
        }
    }

    init {
        val dbHelper = DBHelper.getInstance(context)
        db = dbHelper!!.writableDatabase
    }
}