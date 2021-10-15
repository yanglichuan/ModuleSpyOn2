package com.ushareit.spyon.filedetail
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.ushareit.spyon.Config
import com.ushareit.spyon.R
import java.io.File

class FileDetailActivity : AppCompatActivity() {

    private val fileDetailViewModel by viewModels<FileDetailViewModel> {
        FileDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_detail_activity)


        var file = intent.getSerializableExtra(Config.DIR) as File
        file.apply {
            val editText = findViewById<AppCompatEditText>(R.id.editText)
            val ivImage = findViewById<AppCompatImageView>(R.id.ivImage)



            var isImage= false
            for(i in 0 until Config.imageFile.size){
                if(file.name.endsWith(Config.imageFile[i])){
                    isImage =true
                    break
                }
            }

            if(isImage){
                ivImage.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
            }else{
                editText.setText(String(file.readBytes()))
            }
        }

        findViewById<View>(R.id.btFresh).setOnClickListener {
            val editText = findViewById<AppCompatEditText>(R.id.editText)
            editText.setText(String(file.readBytes()))
            Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btSave).setOnClickListener {
            val editText = findViewById<AppCompatEditText>(R.id.editText)
            val toString = editText.text.toString()
            file.writeBytes(toString.toByteArray())

            editText.setText(String(file.readBytes()))

            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show()
        }
    }


}