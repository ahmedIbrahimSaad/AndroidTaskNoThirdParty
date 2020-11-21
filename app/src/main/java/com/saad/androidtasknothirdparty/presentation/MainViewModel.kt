package com.saad.androidtasknothirdparty.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saad.androidtasknothirdparty.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var occurrences = HashMap<String, Int>()
    private var liveOccurrences = MutableLiveData<MutableMap<String, Int>>()
    private var isConnected = MutableLiveData<Boolean>()
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    fun getWords(context: Context,isConnected:Boolean): MutableLiveData<MutableMap<String, Int>>? {

        scope.launch {
            occurrences =
                Injector.provideRepo(context)
                    .getWords(isConnected) as HashMap<String, Int>
            liveOccurrences.postValue(occurrences)
        }
        return liveOccurrences
    }

    fun checkNetworkConnection(context: Context): LiveData<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork
            if (nw == null) {
                isConnected.postValue(false)
                return isConnected
            }
            val actNw = connectivityManager.getNetworkCapabilities(nw)
            if (actNw == null) {
                isConnected.postValue(false)
                return isConnected
            }
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    isConnected.postValue(true)
                    isConnected
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    isConnected.postValue(true)
                    isConnected
                }
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    isConnected.postValue(true)
                    isConnected
                }
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                    isConnected.postValue(true)
                    isConnected
                }

                else -> {
                    isConnected.postValue(false)
                    isConnected
                }
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo
            if (nwInfo == null)  {
                isConnected.postValue(false)
                return isConnected
            }
            isConnected.postValue(nwInfo.isConnected)
            return isConnected
        }


    }

    fun observeConnectivity(): LiveData<Boolean> {
        return isConnected
    }
}