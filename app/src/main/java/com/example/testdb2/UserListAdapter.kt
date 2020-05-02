package com.example.testdb2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*

class UserListAdapter(val users: List<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = UserHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        )
        return viewHolder
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserHolder) {
            holder.user = users[position]
        }
    }
}
class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var user: User? = null
        set(value) {
            field = value
            itemView.userTextView.text = value?.user.name.text
            itemView.ageTextView.text = value?.user.age.text
        }
}