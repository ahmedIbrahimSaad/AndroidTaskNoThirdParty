package com.saad.androidtasknothirdparty.data

import android.content.Context
import android.util.Log
import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.db.WordsControl
import com.saad.androidtasknothirdparty.di.Injector
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WordRemoteDataSource(private val context: Context) {
    private var occurrences = HashMap<String, Int>()
    fun getWordsFromNetwork(): MutableMap<String, Int>? {
        Injector.provideNetworkUtil().httpGet(context.applicationContext.getString(R.string.url))?.let { occurrences =
            Injector.provideStringUtiles().splitTextBySpace(it) as HashMap<String, Int>
        }
        WordsControl(context).create(occurrences)
        return occurrences
    }

}