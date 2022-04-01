package nam.zuchu.asm.fragments.features.receipts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentDetailReceiptsBinding

class DetailReceiptFM : Fragment() {
    lateinit var fmDetailReceiptBinding: FragmentDetailReceiptsBinding
    lateinit var adapter: TypesOfNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmDetailReceiptBinding = FragmentDetailReceiptsBinding.inflate(layoutInflater)
//        initRecyclerView()
        initClick()
        return fmDetailReceiptBinding.root
    }

    private fun initClick() {
        fmDetailReceiptBinding.lavAddReceipt.setOnClickListener{
            findNavController().navigate(R.id.action_drawerReceipt_to_addNewDetailReceiptBSFM)
        }
    }

    private fun initRecyclerView() {
        adapter = TypesOfNameAdapter()


        var decoartion: DividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
//        fmTypesOfNameBinding.rvTypesOfName.addItemDecoration(decoartion)
//        fmTypesOfNameBinding.rvTypesOfName.setHasFixedSize(true)
//        fmTypesOfNameBinding.rvTypesOfName.adapter=adapter
    }
}