package com.cleaner.testappremovel

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object Utils {

/*        val uninstallApplication: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val packageName: String = Objects.requireNonNull(intent.data)!!.encodedSchemeSpecificPart
                Toast.makeText(context, "USER UNINSTALL : $packageName", Toast.LENGTH_SHORT).show()
                Log.i("MyLog", "USER UNINSTALL : $packageName")
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        intentFilter.addDataScheme("package")
        registerReceiver(uninstallApplication, intentFilter)*/

/*    fun getIcon(s: String): Drawable {
        var appIcon: Drawable? = null
        val packageInfoList: List<PackageInfo> = packageManager.getInstalledPackages(0)
        for (i in packageInfoList.indices) {
            val packageInfo = packageInfoList[i]
            if (packageInfo.packageName == s) {
                appIcon = packageInfo.applicationInfo.loadIcon(packageManager)
                imageView.setImageDrawable(appIcon)
            }
        }
        return appIcon!!
    }

    fun getName(s: String): String? {
        var appName = ""
        val packageInfoList: List<PackageInfo> = packageManager.getInstalledPackages(0)
        for (i in packageInfoList.indices) {
            val packageInfo = packageInfoList[i]
            if (packageInfo.packageName == s) {
                appName = packageInfo.applicationInfo.loadLabel(packageManager).toString()
                Log.i("MyLog", "USER UNINSTALL : $appName")
            }
        }
        return appName
    }*/
/*
/*    @SuppressLint("QueryPermissionsNeeded")
    fun getAppsInfo() {
        val apps: List<PackageInfo> = packageManager.getInstalledPackages(0)
        for (i in apps.indices) {
            val p = apps[i]
            val newInfo = AppsInfo()
            newInfo.appName = p.applicationInfo.loadLabel(packageManager).toString()
            newInfo.packageAppName = p.packageName
            newInfo.appIcon = p.applicationInfo.loadIcon(packageManager)
            arrayList.add(newInfo)
//            Log.i("myLogggg", res.size.toString())
//            Log.i("myLogggg", newInfo.appname)
//            Log.i("myLogggg", newInfo.pname)
        }

    }

    class AppsInfo {
        var appName = ""
        var packageAppName = ""
        var appIcon: Drawable? = null
    }*/

    @SuppressLint("RemoteViewLayout")
    fun setNotif() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, "Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 100, intent, 0)
//        remoteViews = RemoteViews(packageName, R.layout.custom_notification)
////        remoteViews!!.setImageViewBitmap(R.id.image, getIcon(packageName, context) as Bitmap)
////        remoteViews!!.setTextViewText(R.id.text, getName(packageName, context))
//        remoteViews!!.setOnClickPendingIntent(R.id.root, pendingIntent)

        val notifyIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(this, 200, notifyIntent, 0)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("text")
            setContentText(title)
            setPriority(NotificationCompat.PRIORITY_DEFAULT)
            setContentIntent(notifyPendingIntent)
        }

        with(NotificationManagerCompat.from(this)) {
            notify(200, builder.build())
        }
    }*/


/*        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 100, intent, 0)
        remoteViews = RemoteViews(context!!.packageName, R.layout.custom_notification)
//        remoteViews!!.setImageViewBitmap(R.id.image, getIcon(packageName, context) as Bitmap)
//        remoteViews!!.setTextViewText(R.id.text, getName(packageName, context))
        remoteViews!!.setOnClickPendingIntent(R.id.root, pendingIntent)*/


    /*val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
        setSmallIcon(R.mipmap.ic_launcher)
        setContent(remoteViews)
        priority = NotificationCompat.PRIORITY_DEFAULT
        setContentIntent(notifyPendingIntent)
    }*/

    /*
    fun getIcon(s: String, context: Context?): Drawable {
        var appIcon: Drawable? = null
        val packageInfoList: List<PackageInfo> = context!!.packageManager.getInstalledPackages(0)
        for (i in packageInfoList.indices) {
            val packageInfo = packageInfoList[i]
            if (packageInfo.packageName == s) {
                appIcon = packageInfo.applicationInfo.loadIcon(context.packageManager)
            }
        }
        return appIcon!!
    }

    fun getName(s: String, context: Context?): String? {
        var appName = ""
        val packageInfoList: List<PackageInfo> = context!!.packageManager.getInstalledPackages(0)
        for (i in packageInfoList.indices) {
            val packageInfo = packageInfoList[i]
//            if (packageInfo.packageName == s) {
                appName = packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
                Log.i("MyLog", "USER UNINSTALL : $appName")
//            }
        }
        return appName
    }*/
}