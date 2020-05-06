package com.example.finallab.Adapter.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatTextView
import com.example.finallab.MovieRecycleViewByGenreActivity

import com.example.finallab.R
import com.example.finallab.RetrofitApi.RetrofitCalls


class HomeFragment : Fragment() {

    private lateinit var genresList : ListView
    private lateinit var reloadGenresBtn : ImageButton
    private lateinit var retrofitCalls: RetrofitCalls

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeView: View = inflater.inflate(R.layout.fragment_home, container, false)
        genresList = homeView.findViewById(R.id.genreList)
        reloadGenresBtn = homeView.findViewById(R.id.reloadGenresBtn)
        retrofitCalls = RetrofitCalls()
        reloadGenresBtn.setOnClickListener { reloadGenreList(homeView.context) }

        reloadGenreList(homeView.context)
        return homeView
    }

     fun reloadGenreList(context : Context){
        retrofitCalls.selectGenres(context, genresList)
        genresList.setOnItemClickListener { parent, view, position, id ->
            val genreName = (view as AppCompatTextView).text.toString()
            val intent = Intent(context, MovieRecycleViewByGenreActivity::class.java)
            intent.putExtra("genre", genreName)
            startActivity(intent)
        }

    }

}




