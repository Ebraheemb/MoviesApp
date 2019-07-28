package ebraheem.tmdb.movies.ui.main.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import ebraheem.tmdb.movies.R;

public class FavoritesAdapter  {


    public static class FavoriteMovieItemVH extends RecyclerView.ViewHolder {

        public static final int LAYOUT_RES = R.layout.item_favorite_movie;

        public static FavoriteMovieItemVH createViewHolder(ViewGroup parent) {
            return new FavoriteMovieItemVH(LayoutInflater.from(parent.getContext()).inflate(LAYOUT_RES, parent, false));
        }


        public FavoriteMovieItemVH(View itemView) {
            super(itemView);
        }
    }
}
