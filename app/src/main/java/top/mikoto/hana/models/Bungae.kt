package top.mikoto.hana.models

import java.sql.Timestamp

data class Bungae(var title : String, var author : String, var location : String, var time : Timestamp, var members : List<String>)