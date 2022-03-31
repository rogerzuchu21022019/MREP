package nam.zuchu.asm.fragments.crud

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentAddNewReceiptBottomSheetBinding
import nam.zuchu.asm.models.types.TypesItem
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

@DelicateCoroutinesApi
class AddNewReceiptBottomSheetFM : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var fmAddNewReceipt: FragmentAddNewReceiptBottomSheetBinding
    lateinit var snackbar: Snackbar
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fmAddNewReceipt = FragmentAddNewReceiptBottomSheetBinding.inflate(layoutInflater)
        initClick()
        return fmAddNewReceipt.root
    }

    private fun initClick() {
        fmAddNewReceipt.acbOK.setOnClickListener(this)
        fmAddNewReceipt.acbCancle.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.acbOK -> {
                val status = fmAddNewReceipt.tieNameOfReceipt.text.toString().trim()
                val typesOfName = fmAddNewReceipt.tieStatus.text.toString().trim()
                val type = TypesItem(0, status = status, typeOfName = typesOfName)
                GlobalScope.launch(Dispatchers.IO){
                    apiService.createNewType(status = status, typeOfName = typesOfName)
                }
                var action = AddNewReceiptBottomSheetFMDirections.actionAddNewReceiptBottomSheetFragmentToDrawerReceipt(type)
                findNavController().navigate(action)
                Toast.makeText(
                    requireContext(),
                    "Go to destination successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancle -> Toast.makeText(requireContext(), "Cancle", Toast.LENGTH_SHORT).show()
        }
    }


}