package com.kotlin.amritakumari.voicerecognition

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast

val RECORD_AUDIO = 1
fun checkPermission(activity: Activity, which: Int): Boolean {
    if (Build.VERSION.SDK_INT < 23) {
        return true
    } else {
        when (which) {
            RECORD_AUDIO -> return ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        }
    }
    return false
}

fun askForPermission(which: Int, activity: Activity) {
    if (Build.VERSION.SDK_INT < 23) {
        return
    } else
    {
        when (which) {
            RECORD_AUDIO -> if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED)
                return
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(activity, activity.getString(R.string.perm_required), Toast.LENGTH_LONG).show()
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO)
                }
            }
        }
    }
}