package com.schoolfam.parcher.data.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.user.User
import java.io.Serializable

@Entity(tableName = "students"
//    ,
//    foreignKeys = [
//        ForeignKey(entity = User::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("parent_id")),
//        ForeignKey(entity = User::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("user_id"))]
)
data class Student(
    @ColumnInfo(name = "user_id") var user_id: Long,
    @ColumnInfo(name = "parent_id") var parent_id: Long?,
    @ColumnInfo(name = "section_id") var section_id:Long

): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null
}