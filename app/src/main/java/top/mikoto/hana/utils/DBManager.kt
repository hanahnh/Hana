package top.mikoto.hana.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import top.mikoto.hana.models.Club
import top.mikoto.hana.models.User


class DBManager private constructor(private val context: Context) {
    private val mFirestore = FirebaseFirestore.getInstance()
    private val usersRef = mFirestore.collection("users")
    private val clubsRef = mFirestore.collection("clubs")

    fun addClub(club: Club) {
        //mDatabase.child("clubs").child(club.title).setValue(club)
        //I think I should add sub-elements manually

        //first, check the club id is exists
        if(checkClubExists(club.title)) {
            //add club document and add others literally
            mFirestore.collection("clubs").document("test").set(club)
        }

        //prevent uid mixing, overwrite member here
        //mDatabase.child("clubs").child(club.title).child("users").push().setValue(club.users)
    }

    fun getJoinedClub(user: User)
    {
        mFirestore.collection("users").document(user.uid).collection("clubs").get().addOnCompleteListener {
            if(it.isSuccessful){
                //get clubs
                for (document in it.result!!) {
                    Log.d("MisakaMOE", document.id + " => " + document.data)
                }
            }
        }
    }

    fun checkClubExists(id: String): Boolean {
        var isExists = false
        mFirestore.collection("clubs").document(id).get().addOnSuccessListener {
            if (it.exists()) {
                Log.d("Misakamoe", "Collection Exists")
                isExists = true
            } else {
                Log.d("MisakaMOE", "Collection Empty")
            }
        }
        return isExists
    }

    fun addUser(user: User) {
        mFirestore.collection("users").document(user.uid).set(user)
    }

    fun joinClub(id: String, user: User) {
        try {
            mFirestore.collection("clubs").document(id).collection("users").document(user.uid).set(user)
        } catch (e: Exception) {
            Log.d("MisakaMOE", e.message)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: DBManager? = null

        fun getInstance(context: Context): DBManager {
            if (INSTANCE == null)
            //for prevent memory leak, use applicationContext instead of Activity
                INSTANCE = DBManager(context.applicationContext)
            return INSTANCE!!
        }
    }
}