package com.example.listofpeople.preview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.listofpeople.R
import com.example.listofpeople.data.User
import com.example.listofpeople.databinding.ItemPeopleBinding
import com.example.listofpeople.preview.ui.fragment.PeopleListFragmentDirections
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class RecyclerAdapter(private val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .priority(Priority.HIGH)

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemPeopleBinding.inflate(inflater,parent,false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])

        holder.itemView.setOnClickListener {
            val action = PeopleListFragmentDirections.actionPeopleListFragmentToPeopleInfoFragment(userList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class ViewHolder(private val binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: User){
            binding.userItem = item
            Glide.with(context)
                .load(item.avatar)
                .apply(options)
                .into(binding.imgAvatar)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}