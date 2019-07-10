package com.schoolfam.parcher.data.announcement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.schoolfam.parcher.utility.TypeConverter
import java.io.Serializable
import java.util.*


@Entity(tableName = "announcement")
@TypeConverters(TypeConverter::class)
class Announcement(
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "date") val date:Date
):Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null
}