package com.example.finallab

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finallab.Adapter.RecyclerViewStuff.MovieComplete
import com.example.finallab.Adapter.RecyclerViewStuff.MoviesAdapter
import com.example.finallab.Adapter.RecyclerViewStuff.RecyclerItemClickListener
import com.example.finallab.RetrofitApi.Dto.GetMovieListResponse
import com.example.finallab.RetrofitApi.RetrofitClient
import kotlinx.android.synthetic.main.movie.*
import kotlinx.android.synthetic.main.movie_recycle_view_by_genre_activity.*
import kotlinx.android.synthetic.main.single_movie.*
import retrofit2.Call
import retrofit2.Response
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class SingleMovieActivity : AppCompatActivity() {

    lateinit var mediaController: MediaController
    lateinit var movieImageView: ImageView

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_movie)
        movieImageView = findViewById(R.id.imageView)
        mediaController = MediaController(this)
        var b = 1;

        if(resources.configuration.orientation!=1){
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        val videoUrl:String = intent.getStringExtra("videoUrl")
//        val imageUrl:String = intent.getStringExtra("imageUrl")
        val movieName:String = intent.getStringExtra("movieName")
        val movieDescription:String = intent.getStringExtra("movieDescription")
        val imgBitmap = Utils().getImageBitMap(intent.getStringExtra("imageUrl"), 780)
        Thread.sleep(500)
        if(resources.configuration.orientation==1){
            movieNameView.text = movieName
            movieDescriptionView.text = movieDescription
        }
        if(imgBitmap != null)
            movieImageView.setImageBitmap(imgBitmap)
        videoView2.setVideoURI(Uri.parse(RetrofitClient.getBaseUrl()+videoUrl))

        playIcon.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            videoView2.setMediaController(mediaController)
            mediaController.setAnchorView(videoView2)
            videoView2.keepScreenOn = true
            videoView2.start()
        }


        videoView2.setOnClickListener {
            if(b == 1)
            videoView2.start()
            b--
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            videoView2.setMediaController(mediaController)
            mediaController.setAnchorView(videoView2)
            videoView2.keepScreenOn = true
        }
    }




}