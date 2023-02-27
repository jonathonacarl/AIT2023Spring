package hu.ait.viewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.viewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRed.setOnClickListener{
            binding.root.setBackgroundColor(Color.RED)
        }
        binding.btnBlue.setOnClickListener{
            binding.root.setBackgroundColor(Color.BLUE)
        }
        binding.btnWhite.setOnClickListener{
            binding.root.setBackgroundColor(Color.WHITE)
        }

    }
}