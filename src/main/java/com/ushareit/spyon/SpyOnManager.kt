package com.ushareit.spyon

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Keep
import com.ushareit.spyon.filelist.ApyOnActivity
import java.io.File

/**
 * @author (ylc)
 * @datetime 2021-10-14 14:48 GMT+8
 * @email yanglichuan@ksjgs.com
 * @detail :
 */

object Config{
    const val DIR = "dir"
   val notSupportFile = arrayListOf<String>(".so", ".xml")
    val imageFile = arrayListOf<String>(".png", ".bmp", ".jpg", ".jpeg", ".gif",".0")
}

@Keep
class SpyOnManager {

    fun open(view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(view is ViewGroup){
                view.context?.let { context->
                    val buttonViews = LayoutInflater.from(context).inflate(R.layout.operation_buttons, view)
                    buttonViews.findViewById<View>(R.id.btInner).setOnClickListener {
                        context.startActivity(Intent(context, ApyOnActivity::class.java).apply {
                            putExtra(Config.DIR, context.dataDir)
                        })
                    }
                    buttonViews.findViewById<View>(R.id.btOuter).setOnClickListener {
                        val replace = context.externalCacheDir?.absolutePath?.replace("cache", "")
                        if(File(replace).exists()){
                            context.startActivity(Intent(context, ApyOnActivity::class.java).apply {
                                putExtra(Config.DIR, File(replace))
                            })
                        }
                    }
                }
            }
        }
    }





}