package nam.zuchu.asm.fragments.features.receipts

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentListReceiptBinding
import nam.zuchu.asm.fragments.ui.ReceiptFM
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService


@DelicateCoroutinesApi
class ListReceiptFM : Fragment(),TypesOfNameAdapter.OnClickItemInRecyclerView {
    lateinit var fmReceiptBinding: FragmentListReceiptBinding
    var apiService:APIService = API.getAPI().create(APIService::class.java)
    lateinit var snackbar:Snackbar
    lateinit var adapterType:TypesOfNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fmReceiptBinding = FragmentListReceiptBinding.inflate(layoutInflater)
        initRecyclerView()
        getAllTypes()
        initClick()
        return fmReceiptBinding.root
    }

    private fun getAllTypes() {
        GlobalScope.launch(Dispatchers.Main){
            val response = apiService.getTypeByStatus("Thu")
            adapterType.setDataForAdapter(response.body()!!)
        }
    }

    private fun initRecyclerView() {
        adapterType = TypesOfNameAdapter()
        fmReceiptBinding.rvListReceipt.adapter = adapterType
        adapterType.setOnClickItem(this)
        fmReceiptBinding.rvListReceipt.setHasFixedSize(true)
        fmReceiptBinding.rvListReceipt.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
    fun initClick() {
        fmReceiptBinding.lavAddReceipt.setOnClickListener {
            Toast.makeText(requireContext(), "Receipt add OK", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_drawerReceipt_to_addNewReceiptBottomSheetFragment2)
        }
    }

    override fun onItemClick(position: Int) {

    }



}