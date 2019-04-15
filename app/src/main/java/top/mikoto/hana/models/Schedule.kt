package top.mikoto.hana.models

import java.sql.Timestamp

class Schedule {
    var id : String? = null
    var title : String? = null
    var author : String? = null
    var description : String? = null
    var location : String? = null
    var members : List<String>? = null
    var time : Timestamp? = null

    constructor()

    constructor(id : String, title:String,author:String,description:String,location:String,members:List<String>,time:Timestamp) {
        this.id = id
        this. title = title
        this.author = author
        this.description = description
        this.location = location
        this.members = members
        this.time = time
    }

    constructor(title:String,author:String,description:String,location:String,members:List<String>,time:Timestamp) {
        this. title = title
        this.author = author
        this.description = description
        this.location = location
        this.members = members
        this.time = time
    }

    fun toMap():Map<String,Any> {
        val result = HashMap<String, Any>()
        result["title"] = title!!
        result["author"] = author!!
        result["description"] = description!!
        result["location"] = location!!
        result["members"] = members!!
        result["time"] = time!!
        return result
    }
}