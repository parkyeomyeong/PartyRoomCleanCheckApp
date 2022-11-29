package com.kmc.cleaningcheck.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//table 명이라고 함
@Entity
data class RoomEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name="roomname") val roomname : String,
    @ColumnInfo(name="type") val type : Int,
    @ColumnInfo(name="list") val list : String,
    @ColumnInfo(name="quantity") val quantity : Int,
        )
