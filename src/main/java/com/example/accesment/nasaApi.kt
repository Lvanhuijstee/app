package com.example.accesment

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface nasaApi {

    @GET("/planetary/apod?=api_key")
    suspend fun getNasa(): Response<List<NasaApiX>>

    @GET("/planetary/apod?api_key={word}")
    suspend fun searchNasa(
        @Path("word") word: String
    ): List<NasaApiX>
}