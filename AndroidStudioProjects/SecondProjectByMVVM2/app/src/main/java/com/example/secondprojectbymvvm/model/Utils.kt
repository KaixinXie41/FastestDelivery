package com.example.secondprojectbymvvm.model

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import kotlin.system.exitProcess

class Utils {
    companion object{
        fun showDialog(context:Context, title:String, message:String,toastMessage:String){
            val builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle(title)
                setMessage(message)
                setPositiveButton("Confirm"){_,_ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)

                }
                setNegativeButton("Cancel"){_,_ ->
                }
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}