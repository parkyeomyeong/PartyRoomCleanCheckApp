package com.kmc.cleaningcheck

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.kmc.cleaningcheck.db.AppDatabase
import com.kmc.cleaningcheck.db.RoomDao
import com.kmc.cleaningcheck.db.RoomEntity

class AddCheckListActivity : AppCompatActivity() {

    private lateinit var roomName : EditText
    lateinit var workListAddBtn : Button
    lateinit var restoreAddBtn : Button
    lateinit var listAddBtn : Button
    private var listOrder = 1
    private var restoreOrder = 1

    private lateinit var scrollList : ScrollView
    private lateinit var todoList : LinearLayout
    private lateinit var restoreList : LinearLayout

    private lateinit var myInflater : LayoutInflater

    //DB쪽
    private lateinit var db : AppDatabase
    private lateinit var roomDao : RoomDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_check_list)

        roomName = findViewById(R.id.edt_partyRoom)

        scrollList = findViewById(R.id.scroll_list)
        todoList = findViewById(R.id.todo_list)
        restoreList = findViewById(R.id.restore_list)

        myInflater = LayoutInflater.from(this)
        settingBtns()

        db = AppDatabase.getInstance(this)!!
        roomDao = db.getRoomDao()

    }

    private fun settingBtns(){
        workListAddBtn = findViewById(R.id.add_work_list)
        restoreAddBtn = findViewById(R.id.add_restore_list)
        listAddBtn = findViewById(R.id.list_add_btn)

        workListAddBtn.setOnClickListener {
            val row = myInflater.inflate(R.layout.item_in_add, null, false)
            row.findViewById<TextView>(R.id.list_order).text = listOrder.toString()
            todoList.addView(row)
            listOrder++
        }

        restoreAddBtn.setOnClickListener {
            val row = myInflater.inflate(R.layout.item_in_add, null, false)
            row.findViewById<TextView>(R.id.list_order).text = restoreOrder.toString()
            restoreList.addView(row)
            restoreOrder++
            //추가하면 맨 아래로 자동 스크롤되게 만듬
            scrollList.post {
                scrollList.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }

        listAddBtn.setOnClickListener {
            insertTodoList()
        }

    }

    private fun insertTodoList(){
        val partyRoomName = roomName.text.toString()
        if(partyRoomName.isBlank() || todoList.childCount == 0 || restoreList.childCount == 0){
            Toast.makeText(this@AddCheckListActivity, "항목 채워라", Toast.LENGTH_SHORT).show()
        }else{
            val insertList = ArrayList<RoomEntity>()
            for(i in 0 until todoList.childCount){
                val workList = todoList.getChildAt(i).findViewById<EditText>(R.id.do_work).text.toString()
                val dataRow = RoomEntity(roomname = partyRoomName, type = 0, list = workList, quantity = -1)
                insertList.add(dataRow)
            }
            for(i in 0 until restoreList.childCount){
                val dataRow = RoomEntity(roomname = partyRoomName, type = 1, list = restoreList.getChildAt(i).findViewById<EditText>(R.id.do_work).text.toString(), quantity = 1)
                insertList.add(dataRow)
            }

            Thread{
                roomDao.insertTodoList(insertList)
                runOnUiThread {
                    Toast.makeText(this@AddCheckListActivity, "잘 들어갔어요", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}