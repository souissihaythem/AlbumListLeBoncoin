package com.lordroid.albumlist.ui.albums

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lordroid.albumlist.R
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.ui.detail.AlbumDetailActivity
import com.lordroid.albumlist.utils.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class AlbumListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var albumAdapter: AlbumAdapter? = null
    private lateinit var viewListModel: AlbumListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        linearLayoutManager = LinearLayoutManager(this)
        allAlbumsRecyclerView.layoutManager = linearLayoutManager

        viewListModel =
            ViewModelProvider(this, viewModelFactory).get(AlbumListViewModel::class.java)

        viewListModel.init(page = 1)
        observeListViewModel(viewListModel)

        allAlbumsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (recyclerView.adapter as AlbumAdapter).itemCount - 1) {

                    var count = (recyclerView.adapter as AlbumAdapter).itemCount / Constants.pages

                    viewListModel.init(page = ++count)
                    observeListViewModel(viewListModel)
                }
            }
        })
    }


    private fun observeListViewModel(viewListModel: AlbumListViewModel) {
        viewListModel.getAlbumsListObservable()
            .observe(this, Observer<List<Album>> { albums ->
                if (albumAdapter == null) {
                    albumAdapter = AlbumAdapter(ArrayList(albums)) { album ->
                        val intent = Intent(this, AlbumDetailActivity::class.java)
                        intent.putExtra(Constants.EXTRAS.ALBUM_ID, album.id)
                        startActivity(intent)
                    }
                    allAlbumsRecyclerView.adapter = albumAdapter

                } else {
                    albumAdapter?.addList(albums)
                }
            })
    }
}
