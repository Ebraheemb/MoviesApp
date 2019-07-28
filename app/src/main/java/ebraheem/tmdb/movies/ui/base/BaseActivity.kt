package ebraheem.tmdb.movies.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idescout.sql.SqlScoutServer


open class BaseActivity : AppCompatActivity() {

    private var sqlScoutServer: SqlScoutServer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlScoutServer = SqlScoutServer.create(this, getPackageName())
    }

    override fun onResume() {
        sqlScoutServer?.resume()
        super.onResume()
    }

    override fun onPause() {
        sqlScoutServer?.pause()
        super.onPause()
    }

    override fun onDestroy() {
        sqlScoutServer?.destroy()
        super.onDestroy()
    }
}
