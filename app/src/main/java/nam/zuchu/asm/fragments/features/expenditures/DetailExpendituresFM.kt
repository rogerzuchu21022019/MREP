package nam.zuchu.asm.fragments.features.expenditures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.*
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.users_with_types.DetailExpenditureAdapter
import nam.zuchu.asm.databinding.FragmentDetailExpendituresBinding
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class DetailExpendituresFM : Fragment() {
    lateinit var fmDetailExpenditureBinding: FragmentDetailExpendituresBinding
    lateinit var adapter: DetailExpenditureAdapter
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmDetailExpenditureBinding = FragmentDetailExpendituresBinding.inflate(layoutInflater)
        getDataFromAPI()
        initRecyclerView()
        initClick()
        return fmDetailExpenditureBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main){
            val responseUsers = apiService.getAllUsers()

            if (responseUsers.isSuccessful ) {
                for (data in 0 until responseUsers.body()!!.size) {
                    val userArr = arrayOfNulls<String>(responseUsers.body()!!.size)
                    for (data1 in 0 until responseUsers.body()!!.size) {
                        userArr[data1] = responseUsers.body()!![data1].userName
                        val arrayAdapterUserName = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            userArr
                        )
                        fmDetailExpenditureBinding.autoCompleteUserName.setAdapter(arrayAdapterUserName)

                        fmDetailExpenditureBinding.autoCompleteUserName.onItemClickListener =
                            AdapterView.OnItemClickListener { _, _, i, _ ->
                                var getUserName = responseUsers.body()!![i].userName
                                GlobalScope.launch {
                                    withContext(Dispatchers.Main) {
                                        var responseDetail =
                                            apiService.getUsers(username = getUserName)
                                        if (responseDetail.isSuccessful) {
                                            if (responseDetail.body()!!.UsersWithTypes.isEmpty()){
                                                Toast.makeText(requireContext(),"Data is empty",Toast.LENGTH_SHORT).show()
                                            }

                                            adapter.setDataForAdapter(
                                                responseUsers.body()!!,
                                                responseDetail.body()!!.UsersWithTypes
                                            )

                                        }
                                    }
                                }
                            }
                    }
                }
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


        var decoartion: DividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        fmDetailExpenditureBinding.rvTypesOfName.addItemDecoration(decoartion)
        fmDetailExpenditureBinding.rvTypesOfName.setHasFixedSize(true)
        fmDetailExpenditureBinding.rvTypesOfName.adapter = adapter
    }
}