package top.mikoto.hana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_app_bar)


        bottom_app_bar.setNavigationOnClickListener {
            val bndf = BottomNavigationDrawerFragment.getInstance()
            if(!bndf!!.isAdded)
                bndf.show(supportFragmentManager, bndf.tag)
        }
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_main_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //val inflater = menuInflater
       // inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
