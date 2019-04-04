package top.mikoto.hana.models

data class Used(var title:String,var description:String, var author : String, var image : String, var buyer : Map<String, Int>)