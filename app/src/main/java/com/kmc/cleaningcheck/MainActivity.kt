package com.kmc.cleaningcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.kmc.cleaningcheck.databinding.ActivityMainBinding
import com.kmc.cleaningcheck.db.AppDatabase
import com.kmc.cleaningcheck.db.RoomDao

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var addBtn : Button

    private lateinit var db : AppDatabase
    private lateinit var roomDao : RoomDao

    private lateinit var mainAdapter : MainRecyclerViewAdapter

    private lateinit var roomNameList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addBtn = findViewById(R.id.add_list_btn)

        addBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddCheckListActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        roomDao = db.getRoomDao()

        getAllPartyRoomList()
    }



    private fun getAllPartyRoomList(){
        Thread{
            Log.i("RoomNames", "실행!")
            roomNameList = ArrayList(roomDao.getRoomList())
            Log.i("count!!", roomNameList.size.toString())
//            runOnUiThread {
//                for(i in 0 until roomNameList.size){
//                    Log.i("RoomNames", roomNameList[i])
//                    Toast.makeText(this@MainActivity, roomNameList[i], Toast.LENGTH_SHORT)
//                }
//            }
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView(){
        runOnUiThread{
            mainAdapter = MainRecyclerViewAdapter(this@MainActivity, roomNameList)
            binding.mainRecyclerView.adapter = mainAdapter
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllPartyRoomList()
    }
}