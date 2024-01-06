package com.example.ruanghukum.data.local.room.aiChat

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "AiChat")
@Parcelize
data class AiChat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "message")
    var message: String = "",

    @ColumnInfo(name = "flag")
    var flag: String? = "",

    @ColumnInfo(name = "createdAt")
    var createdAt: String? = "",
) : Parcelable