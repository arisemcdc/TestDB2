package com.example.testdb2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class RoomDB: RoomDatabase() {
    abstract fun userDao(): UsersDAO
}