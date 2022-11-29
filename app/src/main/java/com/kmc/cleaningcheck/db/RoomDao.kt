package com.kmc.cleaningcheck.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao {
    @Query("select roomname from RoomEntity group by roomname")
    fun getRoomList() : List<String>

    @Query("select * from RoomEntity where roomname = :roomName")
    fun getList(roomName : String) : List<RoomEntity>
    @Insert
    fun insertTodoList(todo : List<RoomEntity>)

}