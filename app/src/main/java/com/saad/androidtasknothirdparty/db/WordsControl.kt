package com.saad.androidtasknothirdparty.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

const val KEY="word"
const val VALUE="occurrence"
const val TABLE_NAME="words"
class WordsControl(context: Context): WordDatabaseHandler(context) {

    fun create(occurrence: MutableMap<String, Int>?): Boolean {
        val values = ContentValues()
        val db: SQLiteDatabase = this.writableDatabase
        for ((key, value) in occurrence!!.entries) {
            values.put(KEY, key)
            values.put(VALUE, value)
            db.insert(TABLE_NAME, null, values)
        }

        db.close()
        return true
    }

    fun read(): MutableMap<String,Int>? {
        val recordsList: MutableMap<String,Int> = HashMap()
        val sql = "SELECT * FROM words ORDER BY id DESC"
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val word: String = cursor.getString(cursor.getColumnIndex(KEY))
                val occurrence = cursor.getInt(cursor.getColumnIndex(VALUE))
                recordsList[word] = occurrence
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return recordsList
    }
}