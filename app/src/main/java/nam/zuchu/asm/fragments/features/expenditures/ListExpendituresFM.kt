package nam.zuchu.asm.fragments.features.expenditures
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentExpenditureBinding
import nam.zuchu.asm.databinding.FragmentListExpenditureBinding
import nam.zuchu.asm.databinding.FragmentListReceiptBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class ListExpendituresFM : Fragment(),TypesOfNameAdapter.OnClickItemInRecyclerView{
    lateinit var fmExpenditureBinding: FragmentListExpenditureBinding
    lateinit var adapter:TypesOfNameAdapter
    var apiService:APIService = API.getAPI().create(APIService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmExpenditureBinding = FragmentListExpenditureBinding.inflate(layoutInflater)
        initRecyclerView()
        getDataFromAPI()
        initClick()

        return fmExpenditureBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main){
            val response = apiService.getTypeByStatus("Chi")
            if (response.isSuccessful){
                adapter.setDataForAdapter(response.body()!!)
            }
        }
    }

    private fun initRecyclerView() {
        adapter = TypesOfNameAdapter()
        adapter.setOnClickItem(this)
        fmExpenditureBinding.rvListExpenditure.setHasFixedSize(true)
        fmExpenditureBinding.rvListExpenditure.adapter = adapter
        fmExpenditureBinding.rvListExpenditure.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

    }


    private fun initClick() {
        fmExpenditureBinding.lavAddExpenditure.setOnClickListener {
            Toast.makeText(requireContext(),"Expenditures add OK",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewExpenditureBottomSheetFragment)
        }
    }

    override fun onItemClick(position: Int) {
    }


}