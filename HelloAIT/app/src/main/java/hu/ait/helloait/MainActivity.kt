package hu.ait.helloait

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTime = findViewById<Button>(R.id.btnShowTime)
        btnTime.setOnClickListener {
            val tvTime = findViewById<TextView>(R.id.tvTime)
            tvTime.text = Date(System.currentTimeMillis()).toString()
        }

    }
}