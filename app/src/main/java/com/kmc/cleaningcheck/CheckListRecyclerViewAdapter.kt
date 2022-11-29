package com.kmc.cleaningcheck

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmc.cleaningcheck.databinding.ItemInCheckListBinding
import com.kmc.cleaningcheck.databinding.ItemInMainBinding
import com.kmc.cleaningcheck.db.RoomEntity

class CheckListRecyclerViewAdapter(private val parent : Context, private val checkList : ArrayList<RoomEntity>) : RecyclerView.Adapter<CheckListRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder(binding: ItemInCheckListBinding): RecyclerView.ViewHolder(binding.root){
        val tv_checkbox = binding.checkBox
        val tv_minus_btn = binding.minusBtn
        val tv_count = binding.count
        val tv_plus_btn = binding.plusBtn
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckListRecyclerViewAdapter.MyViewHolder {
        val binding: ItemInCheckListBinding = ItemInCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_checkbox.text = checkList[position].list
        holder.tv_count.text = checkList[position].quantity.toString()
        //단순히 리필목록이 아니라 해야할 일이면 수량 추가,감소 버튼을 못누르게 해야지
//        if(checkList[position].quantity == 0){
//            holder.tv_minus_btn.isEnabled = false
//            holder.tv_plus_btn.isEnabled = false
//        }else{
//            holder.tv_minus_btn.isEnabled = true
//            holder.tv_plus_btn.isEnabled = true
//        }

    }

    override fun getItemCount(): Int {
        return checkList.size
    }


}