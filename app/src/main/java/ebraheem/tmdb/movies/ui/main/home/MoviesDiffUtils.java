package ebraheem.tmdb.movies.ui.main.home;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import ebraheem.tmdb.movies.data.model.Movie;

import java.util.List;

@Deprecated
public class MoviesDiffUtils extends DiffUtil.Callback{

    List<Movie> oldMovieList;
    List<Movie> newMovieList;

    public MoviesDiffUtils(List<Movie> oldMovieList, List<Movie> newMovieList) {
        this.oldMovieList = oldMovieList;
        this.newMovieList = newMovieList;
    }

    @Override
    public int getOldListSize() {
        return oldMovieList.size();
    }

    @Override
    public int getNewListSize() {
        return newMovieList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieList.get(oldItemPosition).getId() == newMovieList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
