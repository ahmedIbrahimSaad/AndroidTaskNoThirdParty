package com.saad.androidtasknothirdparty.data

import com.saad.androidtasknothirdparty.di.Injector
class WordRepo {
    fun getWords(isConnected: Boolean): MutableMap<String, Int>? {
        return if (isConnected) {
            Injector.provideRemoteDataSource().getWordsFromNetwork()
        } else {
            Injector.provideLocalDataSource().getWordsFromDatabase()
        }
    }
}