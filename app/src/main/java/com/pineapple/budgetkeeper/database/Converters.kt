package com.pineapple.budgetkeeper.database

import androidx.room.TypeConverter
import java.util.Date
import java.util.Calendar

class Converters {
  @TypeConverter
  fun fromTimestamp(value: Long?): Calendar? {
      val cal = Calendar.getInstance()
      cal.setTime(Date(value ?: 0))
      return cal
  }

  @TypeConverter
  fun calendarToTimestamp(date: Calendar?): Long? {
      return date?.time?.time
  }

}
