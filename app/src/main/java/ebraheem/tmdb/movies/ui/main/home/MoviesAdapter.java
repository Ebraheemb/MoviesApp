package ebraheem.tmdb.movies.ui.main.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ebraheem.tmdb.movies.data.model.Movie;
import ebraheem.tmdb.movies.databinding.ItemMovieBinding;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemVH> {

    List<Movie> movieList;
    OnMovieClickListener onMovieClickListener;


    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();

    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public synchronized void addMovies(List<Movie> movieList) {
        if (this.movieList != null && movieList != null && movieList.size() > 0) {
            int count = movieList.size();
            this.movieList.addAll(movieList);
            notifyItemRangeInserted(count, movieList.size());
        }
    }

    @NonNull
    @Override

    public MovieItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MovieItemVH.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemVH holder, int position) {
        holder.setOnMovieClickListener(onMovieClickListener);
        holder.bind(movieList.get(position));
    }


    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void clearList() {
        this.movieList = null;
        notifyDataSetChanged();
    }

    public static class MovieItemVH extends RecyclerView.ViewHolder {

        private final ItemMovieBinding bindingItem;
        private Movie movie;
        private OnMovieClickListener onMovieClickListener;

        public static MovieItemVH createViewHolder(ViewGroup parent) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            ItemMovieBinding binding =
                    ItemMovieBinding.inflate(layoutInflater, parent, false);
            return new MovieItemVH(binding);
        }

        public MovieItemVH(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.bindingItem = binding;
        }

        public void bind(Movie movie) {
            this.movie = movie;
            bindingItem.setMovie(movie);
        }

        public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
            this.onMovieClickListener = onMovieClickListener;
            itemView.setOnClickListener(view -> {

                ViewCompat.setTransitionName(bindingItem.imageView, movie.getId() + "_image");
                ViewCompat.setTransitionName(bindingItem.titleTextView, movie.getId() + "_title");
                if (onMovieClickListener != null)
                    onMovieClickListener.onMovieClick(movie, bindingItem.imageView,bindingItem.titleTextView);
            });

        }


    }

}
