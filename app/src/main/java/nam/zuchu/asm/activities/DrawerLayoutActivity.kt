package nam.zuchu.asm.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.DrawerlayoutActivityBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class DrawerLayoutActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: DrawerlayoutActivityBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var navBot: BottomNavigationView
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    var tvName: TextView? = null
    var tvEmail: TextView? = null
    lateinit var imgAvatar:ImageView
    var apiService: APIService = API.getAPI().create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DrawerlayoutActivityBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
//        getDataFromAPI()
        initDrawerLayout()
        initAppBarConfiguration()
        initNavView()
        initBotView()
        initNavController()
        initNavUI()
        initClick()
    }

//    private fun getDataFromAPI() {
//        GlobalScope.launch(Dispatchers.Main) {
//            val responseUser = apiService.getUsers("namok123")
//            if (responseUser.isSuccessful) {
//                var fullName = responseUser.body()!!.fullName
//                var email = responseUser.body()!!.email
//                var avatar = responseUser.body()!!.avartar
//            }
//
//        }
//    }


    private fun initClick() {
        mainActivityBinding.containMain.lavHome.setOnClickListener {
            mainActivityBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    private fun initNavUI() {
        NavigationUI.setupWithNavController(navView, navController = navController)
        NavigationUI.setupWithNavController(navBot, navController = navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        initNavUI()
        return NavigationUI.navigateUp(
            navController = navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

    private fun initNavController() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        initHeaderDrawer()
        val fbName = firebaseUser!!.displayName
        val fbEmail = firebaseUser!!.email
        val strImageUrl = firebaseUser!!.photoUrl
        if (fbName == null) {
            tvName?.visibility = View.GONE
        } else {
            tvName?.visibility = View.VISIBLE
            tvName?.text = fbName
        }
        tvEmail?.text = fbEmail
        Glide.with(this).load(strImageUrl).error(R.drawable.girl).into(imgAvatar)


        navController = findNavController(R.id.fmNavGraph)
    }

    private fun initHeaderDrawer() {
        tvName = mainActivityBinding.navView.getHeaderView(0).findViewById<TextView>(R.id.tvName)
        tvEmail = mainActivityBinding.navView.getHeaderView(0).findViewById<TextView>(R.id.tvEmail)
        imgAvatar =
            mainActivityBinding.navView.getHeaderView(0).findViewById<ImageView>(R.id.ivAvatar)
    }

    private fun initBotView() {
        navBot = mainActivityBinding.containMain.navBot
    }

    private fun initNavView() {
        navView = mainActivityBinding.navView
    }

    private fun initAppBarConfiguration() {
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.drawerHome,
            R.id.drawerExpenditure,
            R.id.drawerReceipt,
            R.id.drawerIntroduce,
            R.id.drawerStatistic
        ).setOpenableLayout(mainActivityBinding.drawerLayout)
            .build()


    }

    private fun initDrawerLayout() {
        drawerLayout = mainActivityBinding.drawerLayout
    }


}