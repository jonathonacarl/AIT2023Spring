package hu.ait.highlowgamecompose.ui.screen.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.Random

class GameViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var generatedNum by mutableStateOf(0)
    var upperBound by mutableStateOf(3)

    init {
        savedStateHandle.get<Int>("upperBound")?.let {
            upperBound = it
        }
        generateNewNum()
    }

    fun generateNewNum() {
        generatedNum = Random(System.currentTimeMillis()).nextInt(upperBound)
    }

}