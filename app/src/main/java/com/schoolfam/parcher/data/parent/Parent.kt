package com.schoolfam.parcher.data.parent

import androidx.lifecycle.ViewModelProviders
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao
import com.schoolfam.parcher.viewModel.UserViewModel
import java.io.Serializable

@Entity(tableName = "parents",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE)]
)
data class Parent(
             @ColumnInfo(name = "user_id") val userId: Long,
            @ColumnInfo(name = "phone_number") val phoneNumber: Long
): Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null


}