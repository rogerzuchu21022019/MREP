package nam.zuchu.asm.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.user.UserInformationsAdapter
import nam.zuchu.asm.databinding.ActivityMainBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService


class MainActivity : AppCompatActivity() {
    private var activityMainBinding: ActivityMainBinding? = null
    private var adapter: UserInformationsAdapter? = null
    lateinit var navController: NavController
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)
        initRecyclerView()
//        GetAllUsersFromAPI()
//        GetAllTypesFromAPI()
//        GetAllUsersWithTypesFromAPI()
        initNavController()
//        CreateNewType()
//        CreateNewUsersWithTypes()
    }

    private fun initNavController() {
        navController = Navigation.findNavController(this@MainActivity, R.id.fmNavGraph1)
    }

    private fun createNewType() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = apiService.createNewType(
                status = "Thu",
                typeOfName = "Xuong Q9"
            )
            if (response.isSuccessful) {
                Log.d("CREATE OK", response.body()!!.toString())
            }
        }
    }

//    private fun CreateNewUsersWithTypes() {
//    }


    private fun getAllUsersWithTypesFromAPI() {
        GlobalScope.launch(Dispatchers.IO) {
            val respone = apiService!!.getAllUsersWithTypes()
            if (respone.isSuccessful) {
                for (data in respone.body()!!) {
                    Log.d("UsersWithTypes", data.toString())
                }
            }
        }
    }

    private fun getAllTypesFromAPI() {
        GlobalScope.launch(Dispatchers.IO) {
            val respone = apiService!!.getAllTypes()
            if (respone.isSuccessful) {
                for (data in respone.body()!!) {
                    Log.d("TYPE", data.toString())
                }
            }
        }
    }

    private fun getAllUsersFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                for (data in response.body()!!) {
                    Log.d("TAG", data.toString())
                    adapter!!.setDataForAdapter(response.body()!!)
                }
            }
        }
    }

    private fun initRecyclerView() {
//        adapter = UserInformationsAdapter()
//        var decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        activityMainBinding!!.rv.hasFixedSize()
//        activityMainBinding!!.rv.addItemDecoration(decoration)
//        activityMainBinding!!.rv.adapter = adapter
    }


}