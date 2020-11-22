package com.saad.androidtasknothirdparty

import android.util.Log

class StringUtil {
     fun splitTextBySpace(result: String) : MutableMap<String, Int>{
        var occurrences = HashMap<String, Int>()

        val splitWords = result.split(" ")
        for (word in splitWords) {
            if (word == "." || word == ">" || word == "<" || word == ","
                || word == "/>" || word == "</" || word == " ' "
                || word == "" || word == "/"|| word == "|"
                || word == "}"|| word == "{"|| word == "-"|| word == "*"
            ) continue
            var oldCount = occurrences[word]
            if (oldCount == null) {
                oldCount = 0
            }
            occurrences[word] = oldCount + 1
        }
        Log.d("hash", occurrences.toString())
        return occurrences
    }
}