package com.example.testdb2

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "users")
class User {
    @PrimaryKey
    var id: Int = 0
    var name: String = "Kirill"
    var age:Int = 16
    @TypeConverters(AccountsConverter::class)
    var dateOfBirth: LocalDate = LocalDate.of(1934,1, 2)
    //var accounts: List<String> = emptyList()
}
class AccountsConverter {
    companion object{
      @TypeConverter
      @JvmStatic
      fun dateOfBirthToJson(dateOfBirth1: LocalDate): String {

         val json = dateOfBirth1.toString()
         // val json:String = Json(JsonConfiguration.Stable).stringify(String.toString(), dateOfBirth)
       //val json:String = Json(JsonConfiguration.Stable).stringify(LocalDate.serializer(), dateOfBirth)
     //fun accountsToJson(accounts:List<String>): String{
       //val json = Json(JsonConfiguration.Stable).toString(LocalDate)
          return json
      }
      @TypeConverter
      @JvmStatic
      fun localDateFromJson(string: String):LocalDate {
      //fun accountsFromJson(string: String):List<String>{
        // val rezult = Json(JsonConfiguration.Stable).parseList<String>(string)
           //val rezult = Json(JsonConfiguration.Stable).parse<LocalDate>(string)
          //val rezult = Json(JsonConfiguration.Stable).parse<String>(LocalDate)
          val rezult = LocalDate.parse(string)
          return rezult
      }
    }
}