package com.prabirkundu.techexactlyassignment.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.prabirkundu.techexactlyassignment.Repository.Repository
import com.prabirkundu.techexactlyassignment.Util.Resource
import com.prabirkundu.techexactlyassignment.model.App
import com.prabirkundu.techexactlyassignment.model.AppListModel
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository):ViewModel(){
    private val TAG = "MainViewModel"
    val appList: MutableLiveData<Resource<MutableList<App>>> = MutableLiveData()
    var appListResponse:  MutableList<App>? = null

    fun getAppLIst(kid_id:String) = viewModelScope.launch {
        val response = repository.getAppList(kid_id,object : Repository.Callback {
            override fun <T> onSuccess(res: T) {
                if (res is AppListModel) {
                    Log.e(TAG, "onSuccess: " + res.success)
                    if (res.success) {
                        val list = res.data.app_list as MutableList
                        appList.postValue(Resource.Success(appListResponse ?: list))
                    } else {
                        appList.postValue(Resource.Error(res.message))
                    }
                } else {
                    Log.e(TAG, "failure: called")
                    appList.postValue(Resource.Error("Error"))
                }
            }

            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onFailure(errorMessage: String) {
                appList.postValue(Resource.Error(errorMessage))
            }

        })

    }
}