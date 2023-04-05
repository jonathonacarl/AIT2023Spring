package hu.ait.tododemo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDAO {
    @Query("SELECT * from todotable")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoItem: TodoItem)

    @Update
    suspend fun update(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Query("DELETE from todotable")
    suspend fun deleteAllTodos()
}