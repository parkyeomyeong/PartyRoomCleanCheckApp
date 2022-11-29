package com.kmc.cleaningcheck

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmc.cleaningcheck.databinding.ItemInMainBinding
import com.kmc.cleaningcheck.db.RoomEntity


class MainRecyclerViewAdapter(private val parent : Context, private val roomList : ArrayList<String>) : RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>(){
    inner class MyViewHolder(binding: ItemInMainBinding): RecyclerView.ViewHolder(binding.root){
        val tv_partyRooom_name = binding.partyRoomName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemInMainBinding = ItemInMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //파티룸 이름 알맞게 넣어서 뿌릴꺼야
        holder.tv_partyRooom_name.text = roomList[position].toString()
        holder.tv_partyRooom_name.setOnClickListener {
            val intent = Intent(it.context, CleanCheckList::class.java)
            intent.putExtra("partyRoomName", roomList[position])
            parent.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}