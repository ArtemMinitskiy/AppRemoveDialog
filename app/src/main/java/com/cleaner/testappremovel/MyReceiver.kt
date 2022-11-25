package com.cleaner.testappremovel

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.util.*

class MyReceiver : BroadcastReceiver() {

    @SuppressLint("RemoteViewLayout")
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("MyLog", "MyReceiver onReceive")

        var packageName = ""
        try {
            packageName = Objects.requireNonNull(intent!!.data)!!.encodedSchemeSpecificPart
            Toast.makeText(context, "USER UNINSTALL : $packageName", Toast.LENGTH_SHORT).show()
            val mDialogIntent = Intent(context!!.applicationContext, MyAlertDialog::class.java)
            mDialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(mDialogIntent)
            Log.i("MyLog", "USER UNINSTALL : $packageName")
        } catch (ex: Exception) {
            Log.i("MyLog", "Exception: $ex")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(Intent(context, MyService::class.java))
        } else {
            context!!.startService(Intent(context, MyService::class.java))
        }

    }

}