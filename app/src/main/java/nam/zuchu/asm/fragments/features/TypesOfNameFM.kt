package nam.zuchu.asm.fragments.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentTypesOfNameBinding

class TypesOfNameFM : Fragment() {
    lateinit var fmTypesOfNameBinding: FragmentTypesOfNameBinding
    lateinit var adapter: TypesOfNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmTypesOfNameBinding = FragmentTypesOfNameBinding.inflate(layoutInflater)
        initRecyclerView()
        return fmTypesOfNameBinding.root
    }

    private fun initRecyclerView() {
        adapter = TypesOfNameAdapter()


        var decoartion: DividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        fmTypesOfNameBinding.rvTypesOfName.addItemDecoration(decoartion)
        fmTypesOfNameBinding.rvTypesOfName.setHasFixedSize(true)
        fmTypesOfNameBinding.rvTypesOfName.adapter=adapter
    }
}