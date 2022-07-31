package com.cleaner.testappremovel

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.util.*

class MyReceiver : BroadcastReceiver() {
    var list: ArrayList<MyService.AppsInfo> = ArrayList()
    var str = ""
    lateinit var icon: Drawable

    @SuppressLint("RemoteViewLayout")
    override fun onReceive(context: Context?, intent: Intent?) {

        list = MyService.arrayList

        var packageName = ""
        try {
            packageName = Objects.requireNonNull(intent!!.data)!!.encodedSchemeSpecificPart
            Toast.makeText(context, "USER UNINSTALL : $packageName", Toast.LENGTH_SHORT).show()
//            startDialog(context)
            Log.i("MyLog", "USER UNINSTALL : $packageName")
        } catch (ex: Exception) {
            Log.i("MyLog", "Exception: $ex")
        }

        for (i in list.indices) {
            if (packageName == list[i].packageAppName) {
                str = list[i].appName
                icon = list[i].appIcon!!
            }
        }

        val mDialogIntent = Intent(context!!.applicationContext, MyAlertDialog::class.java)
        mDialogIntent.putExtra("AppName", str)
        mDialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(mDialogIntent)

        Log.i("MyLog", str)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(Intent(context, MyService::class.java))
        } else {
            context!!.startService(Intent(context, MyService::class.java))
        }

    }

    private fun startDialog(context: Context?) {
        val mDialogIntent = Intent(context!!.applicationContext, MyAlertDialog::class.java)
        mDialogIntent.putExtra("AppName", str)
        mDialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context!!.startActivity(mDialogIntent)
    }

}