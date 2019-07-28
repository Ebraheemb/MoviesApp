package ebraheem.tmdb.movies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Objects


@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    var id: Long = 0,

    @field:SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    @field:SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @field:SerializedName("overview")
    var overview: String? = null,

    @field:SerializedName("original_language")
    var originalLanguage: String? = null,

    @field:SerializedName("popularity")
    var popularity: Double?,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    @field:SerializedName("vote_count")
    var voteCount: Int?,

    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false

)
