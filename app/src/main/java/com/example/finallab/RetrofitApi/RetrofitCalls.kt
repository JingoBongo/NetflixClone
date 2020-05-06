package com.example.finallab.RetrofitApi

import android.R
import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finallab.Adapter.RecyclerViewStuff.MovieComplete
import com.example.finallab.Adapter.RecyclerViewStuff.MoviesAdapter
import com.example.finallab.RetrofitApi.Dto.DefaultResponse
import com.example.finallab.RetrofitApi.Dto.GetGenresResponse
import com.example.finallab.RetrofitApi.Dto.GetMovieListResponse
import retrofit2.Call
import retrofit2.Response

class RetrofitCalls {
     fun sendWish(context: Context, wish: String) {
        if (!wish.isNullOrEmpty() || !wish.equals("")) {
            //do the sql
            RetrofitClient.instance.createWish(wish)
                .enqueue(object : retrofit2.Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponse>,
                        response: Response<DefaultResponse>
                    ) {
                        Toast.makeText(
                            context,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        } else {
            if (wish.isEmpty()) {
                Toast.makeText(context, "Suggestion can't be empty", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

     fun selectGenres(context: Context, genresList: ListView    ){
        RetrofitClient.instance.selectGenres().enqueue(object: retrofit2.Callback<List<GetGenresResponse>>{
            override fun onFailure(call: Call<List<GetGenresResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<GetGenresResponse>>,
                response: Response<List<GetGenresResponse>>
            ) {
                val arrayAdapter: ArrayAdapter<*>
                val genreArray = ArrayList<String>()
                for(item: GetGenresResponse in response.body()!!){
                    genreArray.add(item.MovieGenre)
                }
                arrayAdapter = ArrayAdapter(context,
                    R.layout.simple_list_item_1, genreArray)
                genresList.adapter = arrayAdapter
                Toast.makeText(context, "Genres updated", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun selectMoviesByName(context: Context, name: String, moviesRecyclerView: RecyclerView) {
        RetrofitClient.instance.getMovieListByName(name).enqueue(object: retrofit2.Callback<List<GetMovieListResponse>>{
            override fun onFailure(call: Call<List<GetMovieListResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<GetMovieListResponse>>,
                response: Response<List<GetMovieListResponse>>
            ) {

                val movieArray = ArrayList<MovieComplete>()
                for(item: GetMovieListResponse in response.body()!!){
                    movieArray.add(MovieComplete(item.id, item.ImageUrl , item.MovieDescription, item.MovieName, item.VideoUrl))
                }

                moviesRecyclerView.layoutManager = LinearLayoutManager(context)
                moviesRecyclerView.adapter = MoviesAdapter(movieArray)
                Toast.makeText(context, "Movies updated", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun selectMoviesByGenre(context: Context, genre: String, moviesRecyclerView: RecyclerView) {
        RetrofitClient.instance.getMovieList(genre).enqueue(object: retrofit2.Callback<List<GetMovieListResponse>>{
            override fun onFailure(call: Call<List<GetMovieListResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<GetMovieListResponse>>,
                response: Response<List<GetMovieListResponse>>
            ) {
                val movieArray = ArrayList<MovieComplete>()
                for(item: GetMovieListResponse in response.body()!!){
                    movieArray.add(MovieComplete(item.id, item.ImageUrl , item.MovieDescription, item.MovieName, item.VideoUrl))
                }
                moviesRecyclerView.layoutManager = LinearLayoutManager(context)
                moviesRecyclerView.adapter = MoviesAdapter(movieArray)
                Toast.makeText(context, "Movies updated", Toast.LENGTH_SHORT).show()
            }
        })
    }


}