package com.ushareit.spyon.filelist
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ushareit.spyon.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var fileCount: Int = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val fileNumberTextView: TextView = itemView.findViewById(R.id.fileitem_number_text)

        fun bind(fileCount: Int) {
            fileNumberTextView.text = fileCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(fileCount)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateFileCount(updatedFileCount: Int) {
        fileCount = updatedFileCount
        notifyDataSetChanged()
    }
}
