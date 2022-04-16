package nam.zuchu.asm.fragments.features.expenditures

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.users_with_types.DetailExpenditureAdapter
import nam.zuchu.asm.databinding.FragmentDetailExpendituresBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class DetailExpendituresFM : Fragment(),DetailExpenditureAdapter.OnClickItemInRecyclerView{
    lateinit var fmDetailExpenditureBinding: FragmentDetailExpendituresBinding
    lateinit var adapter: DetailExpenditureAdapter
    lateinit var snackbar: Snackbar
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmDetailExpenditureBinding = FragmentDetailExpendituresBinding.inflate(layoutInflater)
        initRecyclerView()
        getDataFromAPI()
        initClick()
        return fmDetailExpenditureBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            fmDetailExpenditureBinding.pbWait.visibility = View.VISIBLE
            val responseUsers = apiService.getAllUsers()
            val responseDetail = apiService.getAllUsersWithTypes()
            // TODO: For each all users and get each user in list
            if (responseUsers.isSuccessful) {
                for (data in 0..responseUsers.body()!!.size){
                    adapter.setDataForAdapter(
                        responseUsers.body()!!,
                        responseDetail.body()!!
                    )
                }
                fmDetailExpenditureBinding.pbWait.visibility = View.GONE

//                for (data in 0 until responseUsers.body()!!.size) {
//                    val userArr = arrayOfNulls<String>(responseUsers.body()!!.size)
//                    for (data1 in 0 until responseUsers.body()!!.size) {
//                        userArr[data1] = responseUsers.body()!![data1].userName
//                        val arrayAdapterUserName = ArrayAdapter(
//                            requireActivity().applicationContext,
//                            android.R.layout.simple_expandable_list_item_1,
//                            userArr
//                        )
//                        fmDetailExpenditureBinding.autoCompleteUserName.setAdapter(
//                            arrayAdapterUserName
//                        )
//                        // TODO: get information of user when choose username
//                        fmDetailExpenditureBinding.autoCompleteUserName.onItemClickListener =
//                            AdapterView.OnItemClickListener { _, _, i, _ ->
//                                val getUserName = responseUsers.body()!![i].userName
//                                GlobalScope.launch {
//                                    withContext(Dispatchers.Main) {
//                                        val responseDetail =
//                                            apiService.getUsers(username = getUserName)
//                                        val responseAllUserWithType = apiService.getAllUsersWithTypes()
//                                        if (responseDetail.isSuccessful && responseAllUserWithType.isSuccessful) {
//                                            if (responseDetail.body()!!.UsersWithTypes.isEmpty()) {
//                                                Toast.makeText(
//                                                    requireContext(),
//                                                    "Data is empty",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                            }
//
//                                        }
//                                    }
//                                }
//                            }
//                    }
//                }
            }
        }
    }
    private fun initClick() {
        fmDetailExpenditureBinding.lavAddExpenditure.setOnClickListener {
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewDetailExpenditureBSFM)
        }
    }

    private fun initRecyclerView() {
        adapter = DetailExpenditureAdapter()
        adapter.setOnClickItem(this)
        var decoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        fmDetailExpenditureBinding.rvTypesOfName.addItemDecoration(decoration)
        fmDetailExpenditureBinding.rvTypesOfName.setHasFixedSize(true)
        fmDetailExpenditureBinding.rvTypesOfName.adapter = adapter
    }

    override fun onClickItem(position: Int,view: View) {
        if(position==-1){
            return
        }else{
            snackbar = Snackbar.make(requireView(),"Position $position",1000)
            snackbar.show()
        }
    }


}