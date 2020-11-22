package com.saad.androidtasknothirdparty

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NetWorkUtil {
     fun httpGet(myURL: String?)  : String?  {

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

}