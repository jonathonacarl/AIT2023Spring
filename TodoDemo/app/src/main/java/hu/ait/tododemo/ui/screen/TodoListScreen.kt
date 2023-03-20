package hu.ait.tododemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.tododemo.data.TodoItem
import hu.ait.tododemo.data.TodoPriority
import java.util.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hu.ait.tododemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = viewModel(),
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        TopAppBar(
            title = {
                Text("AIT Todo")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor =
                MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = {
                    navController.navigate(
                        "summary/${todoListViewModel.getAllTodoNum()}/${todoListViewModel.getImportantTodoNum()}"
                    )
                }) {
                    Icon(Icons.Filled.Info, null)
                }
            })


        AddNewTodoForm()

        LazyColumn() {
            items(todoListViewModel.getAllToDoList()) {
                TodoCard(todoItem = it,
                    onTodoCheckChange = { checked ->
                        todoListViewModel.changeTodoState(it, checked)
                    },
                    onRemoveItem = {
                        todoListViewModel.removeTodoItem(it)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTodoForm(
    todoListViewModel: TodoListViewModel = viewModel()
) {
    var newTodoTitle by remember { mutableStateOf("") }
    var newTodoDesc by remember { mutableStateOf("") }
    var newTodoPriority by remember { mutableStateOf(false) }

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(value = newTodoTitle,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Todo title") },
                onValueChange = {
                    newTodoTitle = it
                }
            )
            OutlinedTextField(value = newTodoDesc,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Description") },
                onValueChange = {
                    newTodoDesc = it
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = newTodoPriority, onCheckedChange = {
                newTodoPriority = it
            })
            Text(text = "Important")
        }

        Button(onClick = {
            todoListViewModel.addTodoList(
                TodoItem(
                    UUID.randomUUID().toString(),
                    newTodoTitle,
                    newTodoDesc,
                    Date(System.currentTimeMillis()).toString(),
                    if (newTodoPriority) TodoPriority.HIGH else TodoPriority.NORMAL,
                    false
                )
            )
        }) {
            Text(text = "Add")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoCard(
    todoItem: TodoItem,
    onTodoCheckChange: (Boolean) -> Unit = {},
    onRemoveItem: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {

            Image(
                painter = painterResource(id = todoItem.priority.getIcon()),
                contentDescription = "Priority",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = todoItem.title,
                    textDecoration =
                    if (todoItem.isDone) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
            }

            Row {
                Checkbox(
                    checked = todoItem.isDone,
                    onCheckedChange = {
                        onTodoCheckChange(it)
                    },
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable {
                        onRemoveItem()
                    },
                    tint = Color.Red
                )
            }
        }
    }
}