package ir.hajkarami.coroutines_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.hajkarami.coroutines_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnCount: Button
    private lateinit var btnDownload: Button
    private lateinit var textResult: TextView
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnCount = findViewById(R.id.btn_count)
        btnDownload = findViewById(R.id.btn_download)
        textResult = findViewById(R.id.txt_counter)

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

private fun MainActivity.download() {
    for (i in 1..2000000000) {
        Log.i("TAGY", "download: $i")
    }
}
