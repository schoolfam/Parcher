package com.schoolfam.parcher.utility

import androidx.room.TypeConverter
import java.util.*

class TypeConverter {

    @TypeConverter
    fun fromTimeStamp(value:Long?):Date{
        return if(value==null){
            Date()
        } else{
            Date(value)
        }
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?):Long{
        return date?.time ?: 0
    }

}