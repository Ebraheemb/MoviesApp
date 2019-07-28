package ebraheem.tmdb.movies.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.common.Constants

object BindingAdapters {

    @BindingAdapter("imageUrl", "isBackdrop")
    @JvmStatic fun bindImage(imageView: ImageView, imagePath: String?, isBackdrop: Boolean) {
        if (imagePath == null)
            return
        val baseUrl: String
        if (isBackdrop) {
            baseUrl = Constants.BACKDROP_URL
        } else {
            baseUrl = Constants.IMAGE_URL
        }
        Glide.with(imageView.context)
            .load(baseUrl + imagePath)
            .into(imageView)
    }

     @BindingAdapter("textAverage")
    @JvmStatic fun setAverage(textView: TextView, voteAverage: Double?) {
        if (voteAverage == null)
            return

        textView.text = "$voteAverage/10"
    }


    @BindingAdapter("imageUrl")
    @JvmStatic fun bindImage(imageView: ImageView?, imagePath: String?) {
        if (imagePath == null)
            return
        Glide.with(imageView!!.context)
            .load(Constants.IMAGE_URL + imagePath)
            .into(imageView!!)
    }



    @BindingAdapter("visibleGone")
    @JvmStatic fun showHide(view: View, show: Boolean) {
        if (show)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }


}