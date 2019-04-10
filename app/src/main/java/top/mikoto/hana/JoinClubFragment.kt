package top.mikoto.hana

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_join_club.*
import top.mikoto.hana.models.*
import top.mikoto.hana.utils.DBManager
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [JoinClubFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JoinClubFragment : Fragment(), View.OnClickListener {
    private val dbManager by lazy { DBManager.getInstance(requireContext())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        join_club.setOnClickListener(this)
        create_club.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.join_club -> {
                dbManager.joinClub("test", User("testUID","반김준수","hrh1110@naver.com"))
                //startActivity(Intent(requireContext(),MainActivity::class.java))
                //requireActivity().finish()
            }
            R.id.create_club -> {
                //create club

                var schedule = ArrayList<Schedule>()
                var member = ArrayList<String>()
                member.add("김준수")
                member.add("반준수")
                schedule.add(Schedule("모임","김준수","test","서울특별시청",member, Timestamp(Date().time)))
                dbManager.addClub(Club("test", schedule, Bungae("번개","반준수","선린인터넷고등학교",Timestamp(Date().time),member),ArrayList(), member))

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_club, container, false)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JoinClubFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JoinClubFragment().apply {
                /*arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                */
            }
    }
}
