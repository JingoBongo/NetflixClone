package com.example.finallab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.finallab.Adapter.RecyclerViewStuff.MoviesAdapter
import com.example.finallab.Adapter.RecyclerViewStuff.RecyclerItemClickListener
import com.example.finallab.RetrofitApi.RetrofitCalls
import kotlinx.android.synthetic.main.movie_recycle_view_by_genre_activity.*


class MovieRecycleViewByGenreActivity : AppCompatActivity() {

    private lateinit var retrofitCalls: RetrofitCalls

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_recycle_view_by_genre_activity)
        val genreName:String = intent.getStringExtra("genre")
        retrofitCalls = RetrofitCalls()
        reloadMovieList(this.applicationContext, genreName)
    }

    inline fun RecyclerView.setOnItemClickListener(crossinline listener: (position: Int) -> Unit) {
        addOnItemTouchListener(
            RecyclerItemClickListener(this,
            object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    listener(position)
                }
            })
        )
    }

    fun reloadMovieList(context : Context, genreName: String){
        retrofitCalls.selectMoviesByGenre(context, genreName, moviesRecyclerView)
        moviesRecyclerView.setOnItemClickListener {
            val intent = Intent(context, SingleMovieActivity::class.java)
            intent.putExtra("videoUrl", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).VideoUrl)
            intent.putExtra("imageUrl", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).imageUrl)
            intent.putExtra("movieName", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).MovieName)
            intent.putExtra("movieDescription", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).MovieDescription)
            startActivity(intent)
        }



    }






}