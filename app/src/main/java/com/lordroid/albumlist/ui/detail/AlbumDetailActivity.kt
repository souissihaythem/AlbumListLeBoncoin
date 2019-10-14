package com.lordroid.albumlist.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lordroid.albumlist.R
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.utils.Constants
import com.lordroid.albumlist.utils.glideUrl
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_album_detail.*
import javax.inject.Inject


class AlbumDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        val albumId = intent?.getIntExtra(Constants.EXTRAS.ALBUM_ID, 1)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AlbumDetailViewModel::class.java)
        albumId?.let {
            viewModel.init(albumId = albumId)
            observeViewModel(viewModel)
        }
    }

    private fun observeViewModel(viewModel: AlbumDetailViewModel) {
        viewModel.getRecipeObservable().observe(this, Observer<Album> { album ->
            if (album != null) {
                albumImageView.glideUrl(album.url)
                albumTextView.text = album.title
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        })
    }
}