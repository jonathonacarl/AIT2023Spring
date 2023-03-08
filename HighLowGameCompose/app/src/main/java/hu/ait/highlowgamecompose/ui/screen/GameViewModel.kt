package hu.ait.highlowgamecompose.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Random

class GameViewModel : ViewModel() {

    var generatedNum by mutableStateOf(0)

    init {
        generateNewNum()
    }

    fun generateNewNum() {
        generatedNum = Random(System.currentTimeMillis()).nextInt(10)
    }

}