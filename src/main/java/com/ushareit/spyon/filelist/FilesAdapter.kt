package com.ushareit.spyon.filelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ushareit.spyon.R
import com.ushareit.spyon.data.FileItem

class FilesAdapter(private val onClick: (FileItem) -> Unit) :
    ListAdapter<FileItem, FilesAdapter.FileViewHolder>(FileDiffCallback) {

    class FileViewHolder(itemView: View, val onClick: (FileItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val fileTextView: TextView = itemView.findViewById(R.id.fileitem_text)
        private val fileImageView: ImageView = itemView.findViewById(R.id.fileitem_image)
        private var mCurrentFileItem: FileItem? = null

        init {
            itemView.setOnClickListener {
                mCurrentFileItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(fileItem: FileItem) {
            mCurrentFileItem = fileItem
            fileTextView.text = fileItem.name
            if (fileItem.image != null) {
                fileImageView.setImageResource(fileItem.image)
            } else {
                fileImageView.setImageResource(R.drawable.icon_error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.file_item, parent, false)
        return FileViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val fileItem = getItem(position)
        holder.bind(fileItem)
    }
}

object FileDiffCallback : DiffUtil.ItemCallback<FileItem>() {
    override fun areItemsTheSame(oldItem: FileItem, newItem: FileItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FileItem, newItem: FileItem): Boolean {
        return oldItem.file.absolutePath == newItem.file.absolutePath
    }
}