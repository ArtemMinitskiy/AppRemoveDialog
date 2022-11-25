package com.cleaner.testappremovel

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.cleaner.testappremovel.Constants.CHANNEL_ID
import com.cleaner.testappremovel.Constants.SCREEN_TYPE
import com.cleaner.testappremovel.Constants.SCREEN_TYPE_BATTERY
import com.cleaner.testappremovel.Constants.SCREEN_TYPE_BOOSTER
import com.cleaner.testappremovel.Constants.SCREEN_TYPE_COOLER
import com.cleaner.testappremovel.Constants.SCREEN_TYPE_JUNK

class MyService : Service() {
//    var remoteViews: RemoteViews? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MyLog", "onStartCommand()")

//        someTask()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, "Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val remoteViews = RemoteViews(packageName, R.layout.custom_notification)

        val intent1 = Intent(this, MainActivity::class.java)
        intent1.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent1.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        val pendingIntent1 = PendingIntent.getActivity(this, 100, intent1, 0)
        val intent2 = Intent(this, MainActivity::class.java)
        intent2.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent2.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent2.putExtra(SCREEN_TYPE, SCREEN_TYPE_BOOSTER)
        val pendingIntent2 = PendingIntent.getActivity(this, 200, intent2, 0)
        val intent3 = Intent(this, MainActivity::class.java)
        intent3.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent3.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent3.putExtra(SCREEN_TYPE, SCREEN_TYPE_BATTERY)
        val pendingIntent3 = PendingIntent.getActivity(this, 300, intent3, 0)
        val intent4 = Intent(this, MainActivity::class.java)
        intent4.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent4.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent4.putExtra(SCREEN_TYPE, SCREEN_TYPE_COOLER)
        val pendingIntent4 = PendingIntent.getActivity(this, 400, intent4, 0)
        val intent5 = Intent(this, MainActivity::class.java)
        intent5.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent5.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        intent5.putExtra(SCREEN_TYPE, SCREEN_TYPE_JUNK)
        val pendingIntent5 = PendingIntent.getActivity(this, 500, intent5, 0)

        remoteViews!!.setOnClickPendingIntent(R.id.homeBtn, pendingIntent1)
        remoteViews!!.setOnClickPendingIntent(R.id.boostBtn, pendingIntent2)
        remoteViews!!.setOnClickPendingIntent(R.id.batteryBtn, pendingIntent3)
        remoteViews!!.setOnClickPendingIntent(R.id.cpuBtn, pendingIntent4)
        remoteViews!!.setOnClickPendingIntent(R.id.junkBtn, pendingIntent5)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher).setContent(remoteViews).setPriority(NotificationManager.IMPORTANCE_MIN).setCategory(Notification.CATEGORY_SERVICE).build()
        startForeground(10, notification)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyLog", "Service onDestroy()")
        val intent = Intent()
        intent.action = "servicereload"
        intent.setClass(this, MyReceiver::class.java)
        this.sendBroadcast(intent)
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("MyLog", "Service onCreate()")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        val intent = Intent(applicationContext, this.javaClass)
        intent.setPackage(packageName)
        val pendingIntent = PendingIntent.getService(applicationContext, 1, intent, PendingIntent.FLAG_ONE_SHOT)
        val alarmManager = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, 1000, pendingIntent)
        super.onTaskRemoved(rootIntent)
    }

/*    private fun someTask() {
        Log.i("MyLog", "Service someTask()")
        Thread {
            for (i in 1..50) {
                Log.i("MyLog", "Service i = $i")
                try {
                    TimeUnit.SECONDS.sleep(5)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }*/

}