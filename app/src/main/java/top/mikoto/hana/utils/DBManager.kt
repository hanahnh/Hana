package top.mikoto.hana.utils

import android.content.Context
import com.google.firebase.database.FirebaseDatabase

class DBManager private constructor(private val context: Context){
    private val mDatabase = FirebaseDatabase.getInstance().reference

    private fun addClub()

    companion object {
        @Volatile private var INSTANCE: DBManager? = null

        fun getInstance(context: Context) : DBManager {
            if(INSTANCE == null)
                INSTANCE = DBManager(context)
            return INSTANCE!!
        }
    }
}