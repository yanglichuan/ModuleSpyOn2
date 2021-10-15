package com.ushareit.spyon.data

import android.os.Build
import com.ushareit.spyon.R
import java.io.File

class DataSource {
    fun fileList(dataDir: File): MutableList<FileItem> {
        var list = mutableListOf<FileItem>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            var files = dataDir.listFiles()
            files?.forEach {
                list.add(
                    FileItem(
                        id = 1,
                        name = it.name,
                        image = if (it.isFile) R.drawable.icon_file else R.drawable.icon_dir,
                        file = it
                    )
                )
            }
        }
        return list
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}