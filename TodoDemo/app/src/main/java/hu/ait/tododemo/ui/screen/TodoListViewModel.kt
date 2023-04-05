package hu.ait.tododemo.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.ait.tododemo.MainApplication
import hu.ait.tododemo.data.TodoDAO
import hu.ait.tododemo.data.TodoItem
import hu.ait.tododemo.data.TodoPriority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class TodoListViewModel(
    private val todoDAO: TodoDAO
) : ViewModel() {
    fun getAllToDoList(): Flow<List<TodoItem>> {
        return todoDAO.getAllTodos()
    }
    fun getAllTodoNum(): Int {
        //return _todoList.size
        return 0
    }
    fun getImportantTodoNum(): Int {
        return 0
        //return _todoList.count { it.priority == TodoPriority.HIGH }
    }
    fun addTodoList(todoItem: TodoItem) {
        viewModelScope.launch {
            todoDAO.insert(todoItem)
        }
    }

    fun removeTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            todoDAO.delete(todoItem)
        }
    }

    fun clearAllTodos() {
        viewModelScope.launch {
            todoDAO.deleteAllTodos()
        }
    }

    fun changeTodoState(todoItem: TodoItem, value: Boolean) {
        todoItem.isDone = value
        viewModelScope.launch {
            todoDAO.update(todoItem)
        }
    }


    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
                TodoListViewModel(todoDAO = application.database.todoDao())
            }
        }
    }
}


