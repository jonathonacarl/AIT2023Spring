package hu.bme.aut.tictactoedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.tictactoedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            binding.ticTacToeView.reset()
        }
    }
}