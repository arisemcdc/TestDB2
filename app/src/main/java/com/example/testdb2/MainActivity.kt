package com.example.testdb2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
val users = arrayListOf<User>()
class MainActivity : AppCompatActivity() {
    lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersList.layoutManager =  LinearLayoutManager(this)
        usersList.adapter = UserListAdapter(users)
        val db=Room.databaseBuilder(applicationContext, RoomDB::class.java, "DB").allowMainThreadQueries().build()
        insertButton.setOnClickListener {
            val user = User()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            db.userDao().insert(user)
        }
        loadButton.setOnClickListener {
           val users:LiveData<List<User>> = db.userDao().getUsers()
        }
    }
}
