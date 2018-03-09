package com.lpirro.moovie.app.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.lpirro.moovie.R
import com.lpirro.moovie.data.models.Movie
import com.lpirro.moovie.utils.GlideApp

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var movies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val homeItem = movies[position]
        holder.bind(homeItem)
    }


    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieCover = itemView.findViewById(R.id.ivMovieCover) as ImageView
        private val movieTitle = itemView.findViewById(R.id.tvMovieTitle) as TextView
        private val movieYear = itemView.findViewById(R.id.tvMovieYear) as TextView

        fun bind(movie: Movie){

            GlideApp.with(itemView.context)
                    .load(movie.poster)
                    .centerCrop()
                    .transition(withCrossFade())
                    .override(267, 400)
                    .into(movieCover)

            movieTitle.text = movie.title
            movieYear.text = movie.release_date
        }
    }
}
