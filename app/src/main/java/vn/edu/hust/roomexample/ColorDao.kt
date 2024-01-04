package vn.edu.hust.roomexample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ColorDao {
    @Query("select * from colors")
    suspend fun getAllColors(): Array<Color>

    @Query("select * from colors where _id = :id")
    suspend fun findColorById(id: Int): Array<Color>

    @Insert
    suspend fun insert(color: Color): Long

    @Update
    suspend fun update(color: Color): Int

    @Query("update colors set name = :name where _id = :id")
    suspend fun updateNameById(id: Int, name: String): Int

    @Delete
    suspend fun delete(color: Color): Int

    @Query("delete from colors where _id = :id")
    suspend fun deleteById(id: Int): Int
}