package com.genius.geniusjobskotlin.Retrofit


import com.google.gson.JsonObject
import com.prabirkundu.techexactlyassignment.model.AppListModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiInterface  {
    @POST("v1/apps/list")
    suspend fun getAppList(
        @Query("kid_id") kid_id: String,
    ): Response<AppListModel?>?
}