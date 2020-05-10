package com.example.testdb2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

//val users = arrayListOf<User>()
//val users = MutableLiveData<List<User>>()
class MainActivity : AppCompatActivity() {
    lateinit var userListAdapter: UserListAdapter
    //val users = MutableLiveData<List<User>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //usersList.layoutManager =  LinearLayoutManager(this)
        //usersList.adapter = UserListAdapter(users)
        val db = Room.databaseBuilder(applicationContext, RoomDB::class.java, "DB")
            .allowMainThreadQueries().build()
        val users = db.userDao().getUsers()
        usersList.layoutManager = LinearLayoutManager(this)
        users.value?.let {
            usersList.adapter = UserListAdapter(it)
        }

        users.observe(this, Observer {
            userListAdapter = UserListAdapter(it)
            usersList.adapter = userListAdapter
        }
        )
        insertButton.setOnClickListener {
            val user = User()
            user.id = idEditText.text.toString().toInt()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            user.dateOfBirth = LocalDate.parse(dateEditText.text.toString())
            db.userDao().insert(user)
        }
        deleteButton.setOnClickListener {

            val userId = idEditText.text.toString().toInt()
            //val userName = nameeditText.text.toString()
            //val userAge = ageeditText.text.toString().toInt()
            val findUser = users.value?.find { userId == it.id }
            //val findUser =users.value?.find { userName == it.name && userAge == it.age }
            if (findUser != null) {
                db.userDao().delete(findUser)
            }

        }
        updateButton.setOnClickListener {
            val user = User()
            user.id = idEditText.text.toString().toInt()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            user.dateOfBirth = LocalDate.parse(dateEditText.text.toString())
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
