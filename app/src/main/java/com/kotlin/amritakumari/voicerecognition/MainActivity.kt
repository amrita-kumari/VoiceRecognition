package com.kotlin.amritakumari.voicerecognition

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var voiceRecordManager: VoiceRecordManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        voiceRecordManager = VoiceRecordManager(this, object : VoiceRecordManager.ResultsReceiveListener {
            override fun onResultsReceived(results: ArrayList<String>) {
                val stringBuffer = StringBuffer()
                for (result in results)
                    stringBuffer.append(result).append("\n")
                textResults.text = stringBuffer.toString()
            }
        })
        btnStart.setOnClickListener { view ->
            if (!checkPermission(this, RECORD_AUDIO)) {
                askForPermission(RECORD_AUDIO, this)
            } else {
                startRecording()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        voiceRecordManager.stopListening()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_AUDIO -> if (grantResults.size > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startRecording()
                }
            }
        }
    }

    fun startRecording() {
        voiceRecordManager.startListening()
    }
}
