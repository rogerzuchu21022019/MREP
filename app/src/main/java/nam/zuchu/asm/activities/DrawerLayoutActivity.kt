package nam.zuchu.asm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.DrawerlayoutActivityBinding

class DrawerLayoutActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: DrawerlayoutActivityBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var navBot: BottomNavigationView
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DrawerlayoutActivityBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        initDrawerLayout()
        initAppBarConfiguration()
        initNavView()
        initBotView()
        initNavController()
        initNavUI()
        initClick()
    }


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
        navController = findNavController(R.id.fmNavGraph)
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