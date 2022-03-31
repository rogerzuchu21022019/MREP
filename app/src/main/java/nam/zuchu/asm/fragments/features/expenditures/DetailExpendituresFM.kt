package nam.zuchu.asm.fragments.features.expenditures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentDetailExpendituresBinding

class DetailExpendituresFM : Fragment() {
    lateinit var fmDetailExpenditureBinding: FragmentDetailExpendituresBinding
    lateinit var adapter: TypesOfNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmDetailExpenditureBinding = FragmentDetailExpendituresBinding.inflate(layoutInflater)
//        initRecyclerView()
        initClick()
        return fmDetailExpenditureBinding.root
    }

    private fun initClick() {
        fmDetailExpenditureBinding.lavAddExpenditure.setOnClickListener {
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewDetailExpenditureBSFM)
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