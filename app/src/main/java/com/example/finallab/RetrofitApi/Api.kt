package com.example.finallab.RetrofitApi

import com.example.finallab.RetrofitApi.Dto.DefaultResponse
import com.example.finallab.RetrofitApi.Dto.GetGenresResponse
import com.example.finallab.RetrofitApi.Dto.GetMovieListResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("createWish.php")
    fun createWish(
        @Field("Wish") genre: String
    ):Call<DefaultResponse>

    @GET("getGenres.php")
    fun selectGenres():Call<List<GetGenresResponse>>

    @FormUrlEncoded
    @POST("getMovieListByGenre.php")
    fun getMovieList (
        @Field( "MovieGenre") genre: String
    ):Call<List<GetMovieListResponse>>

    @FormUrlEncoded
    @POST("getMovieListByName.php")
    fun getMovieListByName (
        @Field( "MovieName") genre: String
    ):Call<List<GetMovieListResponse>>


    @POST("getImage.php")
    fun getImage(
        @Field("Url") url: String
    ): Call<DefaultResponse>



}