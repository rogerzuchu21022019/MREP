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
                // TODO: Push New Type Model 
                val status = fmAddNewReceipt.tieStatus.text.toString().trim()
                val typesOfName = fmAddNewReceipt.tieNameOfReceipt.text.toString().trim()
                GlobalScope.launch(Dispatchers.IO){
                    // TODO: When Add new request,In Layout XML of Fragment, Data will get by STT in Layout XML
                    // TODO: So I will notice it to get data exactly better
                    apiService.createNewType(  status = status, typeOfName = typesOfName)// TODO: parameters same in the layout FragmentAddNewReceiptBottomSheetBinding
                }

                findNavController().navigate(R.id.action_addNewReceiptBottomSheetFragment_to_drawerReceipt)
                Toast.makeText(
                    requireContext(),
                    "Go to destination successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancle -> Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
    }


}