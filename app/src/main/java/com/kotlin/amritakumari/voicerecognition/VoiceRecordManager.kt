package com.kotlin.amritakumari.voicerecognition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast

class VoiceRecordManager(private val context: Context, private val resultsReceiveListener : ResultsReceiveListener) : RecognitionListener {

    private var mSpeechRecognizer: SpeechRecognizer
    private var mSpeechRecognizerIntent: Intent

    init {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        mSpeechRecognizer.setRecognitionListener(this)
        mSpeechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                context.packageName)
    }

    fun startListening(){
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent)
    }

    fun stopListening(){
        mSpeechRecognizer.stopListening()
    }

    fun cleanUp(){
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.stopListening()
            mSpeechRecognizer.cancel()
            mSpeechRecognizer.destroy()
        }
    }

    interface ResultsReceiveListener{
        fun onResultsReceived(results : ArrayList<String>)
    }

    override fun onResults(results: Bundle?) {
        if(null != results) {
            resultsReceiveListener.onResultsReceived(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION))
        }
    }

    override fun onError(errorCode: Int) {
        Log.e("VoiceRecordManager","Error "+errorCode)
    }

    override fun onReadyForSpeech(p0: Bundle?) {
    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onEndOfSpeech() {
    }

}