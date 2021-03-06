package com.workshop.workshop.api

import com.workshop.workshop.api.response.ObjectModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Simple interface used to describe the jsonplaceholder.typicode.com API
 */
interface ApiService {
    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com"
        const val PHOTO_END_POINT = "/photos"
    }

    @GET(PHOTO_END_POINT)
    fun getObjects(): Observable<List<ObjectModel>>
}