package com.saad.androidtasknothirdparty.data

import android.content.Context
import android.util.Log
import com.saad.androidtasknothirdparty.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WordRemoteDataSource(val context: Context){
    var occurrences = HashMap<String, Int>()
    fun getWordsFromNetwork(): MutableMap<String, Int>? {
        httpGet(context.applicationContext.getString(R.string.url))?.let { splitTextBySpace(it) }
        return occurrences
    }

    private fun httpGet(myURL: String?)  : String?  {

        val inputStream: InputStream
        // create URL
        val url: URL = URL(myURL)
        // create HttpURLConnection
        val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection
        // make GET request to the given URL
        conn.connect()
        // receive response as inputStream
        inputStream = conn.inputStream
        // convert inputstream to string

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
        var splitWords = result.split(" ")
        for (word in splitWords) {
            var oldCount = occurrences[word]
            if (oldCount == null) {
                oldCount = 0
            }
            if (!word.equals(".") || !word.equals(">") || !word.equals("<") || !word.equals(",") || !word.equals(
                    "/>"
                ) || !word.equals("</") || !word.equals(" ' ") || !word.equals(" ") || !word.equals(
                    "/"
                )
            )
                occurrences.put(word, oldCount + 1)
        }
        Log.d("hash", occurrences.toString())
    }
}