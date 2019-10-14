package com.lordroid.albumlist.ui.albums

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lordroid.albumlist.R
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.utils.glideUrl
import com.lordroid.albumlist.utils.inflate
import kotlinx.android.synthetic.main.album_list_item.view.*

class AlbumAdapter(var items: ArrayList<Album>, private val listener: (Album) -> Unit) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    fun addList(albums: List<Album>) {
        items.addAll(albums)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(parent.inflate(R.layout.album_list_item))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {

        val album: Album = items[position]

        album.let {
            holder.bind(album, listener)
        }
    }

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Album, listener: (Album) -> Unit) = with(itemView) {
            albumName.text = item.title
            albumImage.glideUrl(item.url)
            setOnClickListener { listener(item) }
        }
    }
}