package com.kmc.cleaningcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmc.cleaningcheck.databinding.ActivityCleanCheckListBinding
import com.kmc.cleaningcheck.db.AppDatabase
import com.kmc.cleaningcheck.db.RoomDao
import com.kmc.cleaningcheck.db.RoomEntity

class CleanCheckList : AppCompatActivity() {
    private lateinit var binding : ActivityCleanCheckListBinding

    private lateinit var db : AppDatabase
    private lateinit var roomDao : RoomDao

    private lateinit var checkListAdapter : CheckListRecyclerViewAdapter

    private lateinit var checkList : ArrayList<RoomEntity>

    var partyRoomName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCleanCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        roomDao = db.getRoomDao()

        //이거는 일단 쓰긴 했는데 뭐지 ... 왜 intent가 바로 나올까..
        partyRoomName = intent.getStringExtra("partyRoomName").toString()

        getAllCheckList()
    }

    private fun getAllCheckList() {
        Thread{
            checkList = ArrayList(roomDao.getList(partyRoomName))
            setRecyclerView()
        }.start()
    }
    private fun setRecyclerView(){
        runOnUiThread {
            checkListAdapter = CheckListRecyclerViewAdapter(this@CleanCheckList, checkList)
            binding.checklistRecyclerView.adapter = checkListAdapter
            binding.checklistRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}