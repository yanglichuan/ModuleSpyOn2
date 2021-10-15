package com.ushareit.spyon.data
import androidx.annotation.DrawableRes
import java.io.File

data class FileItem(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    var file:File = File(""),
    val description: String? =null,
)
