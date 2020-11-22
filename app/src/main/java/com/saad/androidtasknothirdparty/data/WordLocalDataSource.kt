package com.saad.androidtasknothirdparty.data

import com.saad.androidtasknothirdparty.db.WordsControl
import com.saad.androidtasknothirdparty.di.Injector

class WordLocalDataSource {
    fun getWordsFromDatabase(): MutableMap<String, Int>? {
        return WordsControl(Injector.provideContext()).read()
    }
}