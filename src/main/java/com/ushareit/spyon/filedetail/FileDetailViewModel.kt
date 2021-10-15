package com.ushareit.spyon.filedetail
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ushareit.spyon.data.DataSource

class FileDetailViewModel(private val datasource: DataSource) : ViewModel() {
}

class FileDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FileDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FileDetailViewModel(
                datasource = DataSource.getDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}