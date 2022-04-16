package nam.zuchu.asm.fragments.features.receipts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.adapters.users_with_types.DetailReceiptAdapter
import nam.zuchu.asm.databinding.FragmentDetailReceiptsBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class DetailReceiptFM : Fragment(),DetailReceiptAdapter.OnClickItemInRecyclerView {
    lateinit var fmDetailReceiptBinding: FragmentDetailReceiptsBinding
    lateinit var adapter: DetailReceiptAdapter
    lateinit var snackbar: Snackbar
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmDetailReceiptBinding = FragmentDetailReceiptsBinding.inflate(layoutInflater)
        initRecyclerView()
        getDataFromAPI()
        initClick()
        return fmDetailReceiptBinding.root
    }

    private fun initClick() {
        fmDetailReceiptBinding.lavAddReceipt.setOnClickListener {
            findNavController().navigate(R.id.action_drawerReceipt_to_addNewDetailReceiptBSFM)
        }
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            fmDetailReceiptBinding.pbWait.visibility = View.VISIBLE

            val responseUsers = apiService.getAllUsers()
            val responseDetail = apiService.getAllUsersWithTypes()

            // TODO: For each all users and get each user in list
            if (responseUsers.isSuccessful) {
                adapter.setDataForAdapter(
                    responseUsers.body()!!,
                    responseDetail.body()!!
                )
                fmDetailReceiptBinding.pbWait.visibility = View.GONE
            }
        }
    }


    private fun initRecyclerView() {
        adapter = DetailReceiptAdapter()
        adapter.setOnClick(this)
        var decoartion: DividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        fmDetailReceiptBinding.rvTypesOfName.addItemDecoration(decoartion)
        fmDetailReceiptBinding.rvTypesOfName.setHasFixedSize(true)
        fmDetailReceiptBinding.rvTypesOfName.adapter = adapter
    }

    override fun onClickItem(position: Int, view: View) {
        if(position==-1){
            return
        }else{
            snackbar = Snackbar.make(requireView(),"Position $position",1000)
            snackbar.show()
        }

    }
}