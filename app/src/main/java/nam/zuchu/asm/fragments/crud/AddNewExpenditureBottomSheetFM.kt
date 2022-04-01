package nam.zuchu.asm.fragments.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentAddNewExpenditureBottomSheetBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class AddNewExpenditureBottomSheetFM : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var fmAddNewExpenditureBinding: FragmentAddNewExpenditureBottomSheetBinding
    lateinit var snackbar: Snackbar
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fmAddNewExpenditureBinding = FragmentAddNewExpenditureBottomSheetBinding.inflate(layoutInflater)
        initClick()
        return fmAddNewExpenditureBinding.root
    }

    private fun initClick() {
        fmAddNewExpenditureBinding.acbOkExpenditure.setOnClickListener(this)
        fmAddNewExpenditureBinding.acbCancleExpenditure.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.acbOkExpenditure -> {
                val typesOfName = fmAddNewExpenditureBinding.tieStatus.text.toString().trim()
                val status = fmAddNewExpenditureBinding.tieNameOfReceipt.text.toString().trim()
                GlobalScope.launch(Dispatchers.IO){
                    apiService.createNewType( typeOfName = typesOfName,status = status)
                }
                findNavController().navigate(R.id.action_addNewExpenditureBottomSheetFragment_to_drawerExpenditure)
                Toast.makeText(
                    requireContext(),
                    "Go to destination successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancleExpenditure -> Toast.makeText(requireContext(), "Cancle", Toast.LENGTH_SHORT).show()
        }
    }


}