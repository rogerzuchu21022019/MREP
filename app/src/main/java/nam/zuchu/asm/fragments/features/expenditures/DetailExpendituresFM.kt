package nam.zuchu.asm.fragments.features.expenditures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.adapters.users_with_types.DetailExpenditureAdapter
import nam.zuchu.asm.databinding.FragmentDetailExpendituresBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class DetailExpendituresFM : Fragment() {
    lateinit var fmDetailExpenditureBinding: FragmentDetailExpendituresBinding
    lateinit var adapter: DetailExpenditureAdapter
    var apiService:APIService = API.getAPI().create(APIService::class.java)
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
            val response = apiService.getAllUsersWithTypes()
            val responseUsers = apiService.getAllUsers()
            if (response.isSuccessful){
                adapter.setDataForAdapter(response.body()!!,responseUsers.body()!!)
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


        var decoartion: DividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        fmDetailExpenditureBinding.rvTypesOfName.addItemDecoration(decoartion)
        fmDetailExpenditureBinding.rvTypesOfName.setHasFixedSize(true)
        fmDetailExpenditureBinding.rvTypesOfName.adapter=adapter
    }
}