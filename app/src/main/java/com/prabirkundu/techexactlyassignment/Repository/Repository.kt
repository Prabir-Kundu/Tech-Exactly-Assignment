package com.prabirkundu.techexactlyassignment.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.prabirkundu.techexactlyassignment.Retrofit.Retrofit
import com.prabirkundu.techexactlyassignment.model.AppListModel

class Repository {
    interface Callback {
        fun <T> onSuccess(list:  T)
        fun onSuccess()
        fun onFailure(errorMessage: String)
    }

    suspend fun getAppList(callback: Callback) {
        val appResponse: MutableLiveData<AppListModel> = MutableLiveData()
        try {
            val response = Retrofit.api.getAppList("378")

            if (response != null) {
                if (response.isSuccessful) {
                    val responseBody = response?.body()
                    if (responseBody != null) {
                        callback.onSuccess(responseBody)
                    } else {
                        callback.onFailure("Response body is null")
                    }
                } else {
                    if (response != null) {
                        callback.onFailure("Error: ${response.code()} - ${response.message()}")
                    }
                }
            }
        } catch (e: Exception) {
            callback.onFailure("Exception: ${e.localizedMessage}")
        }
    }
}