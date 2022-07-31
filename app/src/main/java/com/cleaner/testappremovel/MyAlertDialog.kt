package com.cleaner.testappremovel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MyAlertDialog: Activity() {
    var appName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        appName = intent.getStringExtra("AppName")!!

        showDeleteDialog()
    }

    @SuppressLint("SetTextI18n")
    private fun showDeleteDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this, R.style.MyAlertDialog)
        val viewGroup = findViewById<ViewGroup>(R.id.content)

        val dialogView: View = LayoutInflater.from(this).inflate(R.layout.popup_dialog, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog: androidx.appcompat.app.AlertDialog = builder.create()
        val uninstallText = dialogView.findViewById(R.id.uninstallText) as TextView
        uninstallText.text = "Uninstalled $appName"
        val cleanMbText = dialogView.findViewById(R.id.cleanMbText) as TextView
        cleanMbText.text = "${(3..7).random()} Mb left. Tap to clean"
        val imageViewClose = dialogView.findViewById(R.id.imageViewClose) as ImageView
        val btnClean = dialogView.findViewById(R.id.btnClean) as Button
        alertDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        btnClean.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            alertDialog.dismiss()
            finish()
        }

        imageViewClose.setOnClickListener {
            alertDialog.dismiss()
            finish()
        }

        alertDialog.show()

    }
}