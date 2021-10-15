package com.ushareit.spyon.filelist
import android.os.Build
import androidx.lifecycle.*
import com.ushareit.spyon.data.DataSource
import com.ushareit.spyon.data.FileItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class FilesListViewModel(dir: File, val dataSource: DataSource) : ViewModel() {

     val mFilesLiveData:MutableLiveData<List<FileItem>> by lazy {
         MutableLiveData()
     }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mFilesLiveData.postValue(dataSource.fileList(dir))
            }
        }
    }
}

class FilesListViewModelFactory( private val dir: File) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilesListViewModel(dir,
                dataSource = DataSource.getDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}