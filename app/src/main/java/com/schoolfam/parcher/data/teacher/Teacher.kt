package com.schoolfam.parcher.data.teacher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.user.User
import java.io.Serializable

@Entity(tableName = "teachers"
//    ,
//    foreignKeys = [
//        ForeignKey(
//        entity = User::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("user_id"),
//        onDelete = ForeignKey.CASCADE),
//        ForeignKey(
//            entity = Section::class,
//            parentColumns = arrayOf("id"),
//            childColumns = arrayOf("section_id"),
//            onDelete = ForeignKey.CASCADE)]
)
data class Teacher(
    @ColumnInfo(name = "user_id") var user_id: Long,
    @ColumnInfo(name = "section_id") var section_id:Long
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null
}