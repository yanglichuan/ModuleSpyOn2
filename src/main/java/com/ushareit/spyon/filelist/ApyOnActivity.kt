package com.ushareit.spyon.filelist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ushareit.spyon.Config
import com.ushareit.spyon.R
import com.ushareit.spyon.data.FileItem
import com.ushareit.spyon.filedetail.FileDetailActivity
import java.io.File

class ApyOnActivity : AppCompatActivity() {

    private val fileListViewModel by viewModels<FilesListViewModel> {
        FilesListViewModelFactory(
            intent.getSerializableExtra(Config.DIR) as File,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apy_on)

//        val headerAdapter = HeaderAdapter()
        val filesAdapter = FilesAdapter { fileItem -> adapterOnClick(fileItem) }
//        val concatAdapter = ConcatAdapter(headerAdapter, filesAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = filesAdapter

        fileListViewModel.mFilesLiveData?.observe(this, {
            it?.let {
                filesAdapter.submitList(it as MutableList<FileItem>)
//                headerAdapter.updateFlowerCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    private fun adapterOnClick(fileItem: FileItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (fileItem.file?.isFile) {

                var canSee = true
                for (i in 0 until Config.notSupportFile.size) {
                    if (fileItem.file.name.endsWith(Config.notSupportFile[i])) {
                        canSee = false
                        break
                    }
                }
                if (canSee) {
                    startActivity(Intent(this, FileDetailActivity::class.java).apply {
                        putExtra(Config.DIR, fileItem.file)
                    })
                }
            } else {
                startActivity(Intent(this, ApyOnActivity::class.java).apply {
                    putExtra(Config.DIR, fileItem.file)
                })
            }
        }
    }

    private fun fabOnClick() {
    }
}

