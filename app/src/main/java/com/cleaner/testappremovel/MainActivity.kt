package com.cleaner.testappremovel

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.app.NotificationCompat
import android.widget.RemoteViews
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MyLog", "onCreate()")
        imageView = findViewById(R.id.image)
        imageView.setOnClickListener {
            showDeleteDialog()
        }

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.cancelAll()

        val serviceIntent = Intent(this, MyService::class.java)
//        ContextCompat.startForegroundService(this, serviceIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!isMyServiceRunning(MyService::class.java)) {
                ContextCompat.startForegroundService(this, serviceIntent)
            }
        } else {
            if (!isMyServiceRunning(MyService::class.java)) {
                startService(Intent(this, MyService::class.java))
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addDataScheme("package")
        registerReceiver(MyReceiver(), intentFilter)

    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    @SuppressLint("SetTextI18n")
    private fun showDeleteDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this, R.style.MyAlertDialog)
        val viewGroup = findViewById<ViewGroup>(R.id.content)

        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.popup_dialog, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog: androidx.appcompat.app.AlertDialog = builder.create()
        val uninstallText = dialogView.findViewById(R.id.uninstallText) as TextView
//        uninstallText.text = "Uninstall $appName"
        val cleanMbText = dialogView.findViewById(R.id.cleanMbText) as TextView
        cleanMbText.text = "${(7..12).random()} Mb left. Tap to clean"
        val imageViewClose = dialogView.findViewById(R.id.imageViewClose) as ImageView
        val btnClean = dialogView.findViewById(R.id.btnClean) as Button
        alertDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        btnClean.setOnClickListener {
            alertDialog.dismiss()
        }

        imageViewClose.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()

    }

}