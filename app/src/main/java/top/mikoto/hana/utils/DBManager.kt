package top.mikoto.hana.utils

import android.content.Context
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import top.mikoto.hana.models.Club
import java.lang.Exception

class DBManager private constructor(private val context: Context){
    private val mDatabase = FirebaseDatabase.getInstance().reference

    fun addClub(club: Club)
    {
        mDatabase.child("clubs").child(club.title).setValue(club)
        //prevent uid mixing, overwrite member here
        mDatabase.child("clubs").child(club.title).child("users").push().setValue(club.users)
    }

    fun joinClub(id : String, user : String)
    {
        try {
            mDatabase.child("clubs").child(id).child("users").orderByKey().limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot)
                {
                    var str = dataSnapshot.value.toString().split("=")
                    mDatabase.child("clubs").child(id).child("users").child((str[0].toInt()+1).toString()).setValue(user)
                }
            })
        }
        catch (e : Exception)
        {
            Log.d("MisakaMOE",e.message)
        }
    }

    companion object {
        @Volatile private var INSTANCE: DBManager? = null

        fun getInstance(context: Context) : DBManager {
            if(INSTANCE == null)
                INSTANCE = DBManager(context.applicationContext)
            return INSTANCE!!
        }
    }
}