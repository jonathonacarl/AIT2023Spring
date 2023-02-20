package hu.ait.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlus.setOnClickListener {
            handlePlus()
        }

    }

    private fun handlePlus() {
        try {
            if (binding.etNumA.text.isNotEmpty()) {
                if (binding.etNumB.text.isNotEmpty()) {
                    val numA = binding.etNumA.text.toString().toInt()
                    val numB = binding.etNumB.text.toString().toInt()

                    binding.tvResult.text = "Result: ${numA + numB}"
                } else {
                    binding.etNumB.error = "This field can not be empty"
                }
            } else {
                binding.etNumA.error = "This field can not be empty"
            }
        } catch (e: Exception) {
            binding.tvResult.text = "Error: ${e.localizedMessage}"
        }
    }
}