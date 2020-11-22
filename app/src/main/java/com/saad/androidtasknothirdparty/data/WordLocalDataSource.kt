package com.saad.androidtasknothirdparty.data

import android.content.Context
import com.saad.androidtasknothirdparty.db.WordsControl

class WordLocalDataSource(private val context: Context) {
    fun getWordsFromDatabase(): MutableMap<String, Int>? {
        return WordsControl(context).read()
    }
}