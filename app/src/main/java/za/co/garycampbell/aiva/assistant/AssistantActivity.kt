package za.co.garycampbell.aiva.assistant

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.ClipboardManager
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.media.Ringtone
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import android.speech.tts.TextToSpeech
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.vision.text.TextRecognizer
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import za.co.garycampbell.aiva.R
import za.co.garycampbell.aiva.data.AssistantDatabase
import za.co.garycampbell.aiva.databinding.ActivityAssistantBinding

class AssistantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssistantBinding
    private lateinit var assistantViewModel: AssistantViewModel

    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechRecognizer: TextRecognizer
    private lateinit var recognizerIntent: Intent
    private lateinit var keeper: String

    private var REQUESTCALL = 1
    private var SENDSMS = 2
    private var READSMS = 3
    private var SHAREFILE = 4
    private var SHARETEXTFILE = 5
    private var READCONTACTS = 6
    private var CAPTUREPHOTO = 7

    private var REQUEST_CODE_SELECT_DOC : Int = 100
    private var REQUEST_ENABLE_BT : Int = 101

    private var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var cameraManager: CameraManager
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var cameraID: String
    private lateinit var ringtone: Ringtone

    private var imageIndex: Int = 0
    private lateinit var imgUri: Uri

    private lateinit var helper: OpenWeatherMapHelper

    @Suppress("DEPRECATION")
    private val imageDirectory = Environment.getExternalStorageState(DIRECTORY_PICTURES).toString()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.non_moveable, R.anim.non_moveable)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_assistant)
        val application = requireNotNull(this).application
        val dataSource = AssistantDatabase.getInstance(application).assistantDao
        val viewModelFactory = AssistantViewModelFactory(dataSource, application)

        assistantViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(AssistantViewModel::class.java)

        val adapter = AssistantAdapter()
        binding.recyclerView.adapter = adapter


        AssistantViewModel.



    }
}