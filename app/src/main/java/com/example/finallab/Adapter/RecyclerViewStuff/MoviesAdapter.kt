package com.example.finallab.Adapter.RecyclerViewStuff

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finallab.R
import com.example.finallab.Utils

class MoviesAdapter(val movies: ArrayList<MovieComplete>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.movieName?.text = movies.get(position).MovieName //.replace("\\<.*?>","")
        var b: Bitmap? = Utils().getImageBitMap(movies.get(position).imageUrl)
        holder.movieImage.setImageBitmap(b)
        holder?.movieDescription?.text = movies.get(position).MovieDescription //.replace("\\<.*?>","")
        holder.videoUrl = movies.get(position).VideoUrl

    }

    override fun getItemCount() = movies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.movieName)
        val movieDescription: TextView = itemView.findViewById(R.id.movieDescription)
        val movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        var videoUrl: String = ""
    }
}
