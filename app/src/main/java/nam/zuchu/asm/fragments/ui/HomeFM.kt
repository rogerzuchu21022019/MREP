package nam.zuchu.asm.fragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.adapters.users_with_types.HomeAdapter
import nam.zuchu.asm.adapters.viewpager.TabReceiptViewpagerAdapter
import nam.zuchu.asm.databinding.FragmentHomeBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class HomeFM : Fragment() {
    lateinit var fmHomeBinding: FragmentHomeBinding
    lateinit var adapter:HomeAdapter
    var apiService:APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        initRecyclerView()
        getDataFromAPI()
        return fmHomeBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main){
            val responseUser  = apiService.getAllUsers()
            if (responseUser.isSuccessful){
                adapter.setDataForAdapter(responseUser.body()!!)
            }
        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        var decoartion: DividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        fmHomeBinding.rvUser.addItemDecoration(decoartion)
        fmHomeBinding.rvUser.setHasFixedSize(true)
        fmHomeBinding.rvUser.adapter = adapter
    }


}