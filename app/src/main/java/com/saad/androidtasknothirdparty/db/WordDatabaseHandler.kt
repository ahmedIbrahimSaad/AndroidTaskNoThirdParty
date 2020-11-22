package com.saad.androidtasknothirdparty.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val SCHEMA_NAME = "WORD_DATABASE"
const val INSERT =
    "CREATE TABLE words( id INTEGER PRIMARY KEY AUTOINCREMENT,word TEXT,occurrence INTEGER ) "
const val DROP = "DROP TABLE IF EXISTS words"

open class WordDatabaseHandler(val context: Context) :
    SQLiteOpenHelper(context, SCHEMA_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(INSERT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP)
        onCreate(db)
    }

}