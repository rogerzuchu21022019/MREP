package nam.zuchu.asm.fragments.features.expenditures
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentExpenditureBinding
import nam.zuchu.asm.databinding.FragmentListExpenditureBinding
import nam.zuchu.asm.databinding.FragmentListReceiptBinding

class ListExpendituresFM : Fragment(),View.OnClickListener{
    lateinit var fmExpenditureBinding: FragmentListExpenditureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmExpenditureBinding = FragmentListExpenditureBinding.inflate(layoutInflater)
        initClick()
        return fmExpenditureBinding.root
    }

    private fun initClick() {
        fmExpenditureBinding.lavAddExpenditure.setOnClickListener {
            Toast.makeText(requireContext(),"Expenditures add OK",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewExpenditureBottomSheetFragment)
        }
    }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }


}