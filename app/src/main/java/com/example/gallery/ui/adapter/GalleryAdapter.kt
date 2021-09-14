package com.example.gallery.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.databinding.ItemPictureBinding
import com.example.gallery.interfaces.OnItemClickListener
import com.example.gallery.utils.loadImage

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var listOfPicture: List<String> = arrayListOf()
    private lateinit var onItemClickListener: OnItemClickListener

    fun addList(listOfPicture: List<String>) {
        this.listOfPicture = listOfPicture
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class GalleryViewHolder(
        private val binding: ItemPictureBinding, private var listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(picture: String) {
            binding.itemPicture.loadImage(picture)
        }

        init {
            itemView.setOnClickListener() {
                listener.onItemClick(listOfPicture[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {

        return GalleryViewHolder(
            ItemPictureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.onBind(listOfPicture[position])
    }

    override fun getItemCount(): Int = listOfPicture.size
}