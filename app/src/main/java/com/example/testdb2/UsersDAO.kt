package com.example.testdb2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface UsersDAO {
    @Query("SELECT * FROM users")
    fun getUsers(): MutableLiveData<List<User>>
    @Insert
    fun insert(user: User)
    @Update
    fun update(user: User)
    @Delete
    fun delete(user: User)
}
