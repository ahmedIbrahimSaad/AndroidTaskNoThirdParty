package com.saad.androidtasknothirdparty.data

import android.content.Context
import android.util.Log
import com.saad.androidtasknothirdparty.R
import com.saad.androidtasknothirdparty.db.WordsControl
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WordRemoteDataSource(private val context: Context) {
    private var occurrences = HashMap<String, Int>()
    fun getWordsFromNetwork(): MutableMap<String, Int>? {
        httpGet(context.applicationContext.getString(R.string.url))?.let { splitTextBySpace(it) }
        WordsControl(context).create(occurrences)
        return occurrences
    }

    private fun httpGet(myURL: String?)  : String?  {

        val inputStream: InputStream
        // create URL
        val url = URL(myURL)
        // create HttpURLConnection
        val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection
        // make GET request to the given URL
        conn.connect()
        // receive response as inputStream
        inputStream = conn.inputStream
        // convert input stream to string

        var result = ""
        if (inputStream != null)
            result = convertInputStreamToString(inputStream)

        Log.d("html", result)

        return result
    }

    private fun convertInputStreamToString(inputStream: InputStream): String {
        val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream))

        var line: String? = bufferedReader?.readLine()
        var result = ""

        while (line != null) {
            result += line
            line = bufferedReader?.readLine()
        }

        inputStream.close()
        return result
    }

    private fun splitTextBySpace(result: String) {
        val splitWords = result.split(" ")
        for (word in splitWords) {
            var oldCount = occurrences[word]
            if (oldCount == null) {
                oldCount = 0
            }
            if (word != "." || word != ">" || word != "<" || word != ","
                || word != "/>" || word != "</" || word != " ' " || word != " " || word != "/"|| word != "|"
            )
                occurrences[word] = oldCount + 1
        }
        Log.d("hash", occurrences.toString())
    }
}