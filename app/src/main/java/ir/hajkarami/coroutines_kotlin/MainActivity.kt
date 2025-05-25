package ir.hajkarami.coroutines_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import ir.hajkarami.coroutines_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var btnCount: Button
    private lateinit var btnDownload: Button
    private lateinit var textResult: TextView
    lateinit var textResult2: TextView
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnCount = findViewById(R.id.btn_count)
        btnDownload = findViewById(R.id.btn_download)
        textResult = findViewById(R.id.txt_counter)
        textResult2 = findViewById(R.id.txt_counter2)

        textResult.text = counter.toString()

        btnCount.setOnClickListener {

            textResult.text = (counter++).toString()
            textResult.text = counter.toString()

        }
        btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                download()
            }
        }

    }
}

private suspend fun MainActivity.download() {
    for (i in 1..200000) {
        Log.i("TAGY", "download: $i")
        withContext(Dispatchers.Main) {
            textResult2.text = "${i}in ${Thread.currentThread().name}"
        }
    }
}
