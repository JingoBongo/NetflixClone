package com.example.finallab.Adapter.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.finallab.Adapter.RecyclerViewStuff.MoviesAdapter
import com.example.finallab.Adapter.RecyclerViewStuff.RecyclerItemClickListener

import com.example.finallab.R
import com.example.finallab.RetrofitApi.RetrofitCalls
import com.example.finallab.SingleMovieActivity
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.moviesRecyclerView

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var retrofitCalls : RetrofitCalls
    private lateinit var searchMovieBtn : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchView: View = inflater.inflate(R.layout.fragment_search, container, false)
        retrofitCalls = RetrofitCalls()
        searchMovieBtn = searchView.findViewById(R.id.searchMovieBtn)

        searchMovieBtn.setOnClickListener {
            var movieName : String  = searchMovieEditText.text.toString()
            retrofitCalls.selectMoviesByName(searchView.context, movieName, moviesRecyclerView)

            moviesRecyclerView.setOnItemClickListener {
                (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it)
                val intent = Intent(context, SingleMovieActivity::class.java)
                intent.putExtra("videoUrl", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).VideoUrl)
                intent.putExtra("imageUrl", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).imageUrl)
                intent.putExtra("movieName", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).MovieName)
                intent.putExtra("movieDescription", (moviesRecyclerView.adapter as MoviesAdapter).movies.get(it).MovieDescription)
                startActivity(intent)
            }
        }

        return searchView
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

}
