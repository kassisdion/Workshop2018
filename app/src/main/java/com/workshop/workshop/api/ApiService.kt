package com.workshop.workshop.api

import com.workshop.workshop.api.response.ObjectResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
        const val PHOTO_END_POINT = "/photos"
    }

    @GET(PHOTO_END_POINT)
    fun getObjects(): Single<ObjectResponse>
}