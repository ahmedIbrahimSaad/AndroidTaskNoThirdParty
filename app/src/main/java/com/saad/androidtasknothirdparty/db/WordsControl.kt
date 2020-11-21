package com.saad.androidtasknothirdparty.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

const val KEY="word"
const val VALUE="occurrency"
const val TABLE_NAME="words"
const val ID="id"
class WordsControl(context: Context): WordDatabaseHandler(context) {

    fun create(occurency: MutableMap<String, Int>?): Boolean {
        val values = ContentValues()
        val db: SQLiteDatabase = this.getWritableDatabase()
        for ((key, value) in occurency!!.entries) {
            values.put(KEY, key)
            values.put(VALUE, value)
            db.insert(TABLE_NAME, null, values)
        }

        db.close()
        return true
    }

    fun read(): MutableMap<String,Int>? {
        var recordsList: MutableMap<String,Int> = HashMap<String, Int>()
        val sql = "SELECT * FROM words ORDER BY id DESC"
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id: Int = cursor.getString(cursor.getColumnIndex(ID)).toInt()
                val word: String = cursor.getString(cursor.getColumnIndex(KEY))
                val occurrency = cursor.getInt(cursor.getColumnIndex(VALUE))
                recordsList.put(word,occurrency)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return recordsList
    }
}