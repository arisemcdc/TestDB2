package com.example.testdb2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
//val users = arrayListOf<User>()
val users = MutableLiveData<List<User>>()
class MainActivity : AppCompatActivity() {
    lateinit var userListAdapter: UserListAdapter
    //val users = MutableLiveData<List<User>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersList.layoutManager =  LinearLayoutManager(this)
        usersList.adapter = UserListAdapter(users)
        val db=Room.databaseBuilder(applicationContext, RoomDB::class.java, "DB").allowMainThreadQueries().build()
        users.observe(this, Observer {
            userListAdapter = UserListAdapter(it)
           usersList.adapter = userListAdapter
        }
        )
        insertButton.setOnClickListener {
            val user = User()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            db.userDao().insert(user)
            users.value.add(user)
        }
        deleteButton.setOnClickListener {
            val user = User()
            db.userDao().delete(user)
        }
        updateButton.setOnClickListener {
            val user = User()
            db.userDao().update(user)
        }

        /*insertButton.setOnClickListener {
            val user = User()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            db.userDao().insert(user)
        }
        loadButton.setOnClickListener {
           //val users:LiveData<List<User>> = db.userDao().getUsers()

        }*/
    }
}
