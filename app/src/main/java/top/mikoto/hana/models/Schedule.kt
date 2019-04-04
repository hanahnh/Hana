package top.mikoto.hana.models

import java.sql.Timestamp

data class Schedule (var title : String, var author : String, var description : String, var location : String, var members : List<String>, var time : Timestamp)