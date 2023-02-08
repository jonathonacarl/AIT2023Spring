package hu.ait.aithello

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import hu.ait.aithello.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            val currentTime = Date(System.currentTimeMillis()).toString()

            binding.tvDate.text = currentTime
            Toast.makeText(this, currentTime, Toast.LENGTH_LONG).show()

            revealCard()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = binding.cardView.getRight()
        val y = binding.cardView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(binding.cardView.getWidth().toDouble(),
            binding.cardView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.cardView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.cardView.setVisibility(View.VISIBLE)
        anim.start()
    }

    
}