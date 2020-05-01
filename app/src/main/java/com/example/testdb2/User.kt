package com.example.testdb2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "users")
class User {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String = "Kirill"
    var age:Int = 16
}