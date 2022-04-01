package nam.zuchu.asm.fragments.crud

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentAddNewDetailReceiptBsheetBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService
import java.util.*

@DelicateCoroutinesApi
class AddNewDetailReceiptBSFM : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var fmAddNewReceiptBinding: FragmentAddNewDetailReceiptBsheetBinding
    var calendar: Calendar = Calendar.getInstance()
    var idType:Int?=null
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fmAddNewReceiptBinding =
            FragmentAddNewDetailReceiptBsheetBinding.inflate(layoutInflater)
        initClick()
        getDataFromAPI()

        return fmAddNewReceiptBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            val responseUsers = apiService.getAllUsers()
            val responseTypes = apiService.getTypeByStatus("Thu")
            if (responseUsers.isSuccessful && responseTypes.isSuccessful) {
                // TODO: Get Api UserName Of user
                for (data in 0 until responseUsers.body()!!.size) {
                    val userArr = arrayOfNulls<String>(responseUsers.body()!!.size)
                    for (data1 in 0 until responseUsers.body()!!.size) {
                        userArr[data1] = responseUsers.body()!![data1].userName
                        val arrayAdapterUserName = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            userArr
                        )
                        fmAddNewReceiptBinding.autoCompleteUserName.setAdapter(arrayAdapterUserName)
                    }
                }

                // TODO: Get Api Status Of Receipt
                for (data in 0 until responseTypes.body()!!.size) {
                    val typeArr = arrayOfNulls<String>(responseTypes.body()!!.size)
                    for (data1 in 0 until responseTypes.body()!!.size) {
                        if (responseTypes.body()!![data1].status=="Thu" && (responseTypes.body()!![data1].typeOfName!="")){
                            typeArr[data1] = responseTypes.body()!![data1].typeOfName
                            val arrayAdapterType = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_expandable_list_item_1,
                                typeArr
                            )
                            fmAddNewReceiptBinding.autoCompleteID.setAdapter(arrayAdapterType)
                            fmAddNewReceiptBinding.autoCompleteID.onItemClickListener =
                                OnItemClickListener { _, _, i, _ ->
                                    idType = responseTypes.body()!![i].idType
                                    fmAddNewReceiptBinding.tvID.text = idType.toString()
                                }
                        }

                    }
                }
            }

        }
    }

    private fun initClick() {
        fmAddNewReceiptBinding.acbCancleDetail.setOnClickListener(this)
        fmAddNewReceiptBinding.acbOkDone.setOnClickListener(this)
        fmAddNewReceiptBinding.acbCalenderReceipt.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.acbOkDone -> {
                val date: String = fmAddNewReceiptBinding.acbCalenderReceipt.text.toString().trim()
                val description: String =
                    fmAddNewReceiptBinding.tieDescription.text.toString().trim()
                val totalMoney: Double = fmAddNewReceiptBinding.tieTotal.text.toString().toDouble()
                val idType: Int = fmAddNewReceiptBinding.tvID.text.toString().toInt()
                val userName: String =
                    fmAddNewReceiptBinding.autoCompleteUserName.text.toString().trim()

                GlobalScope.launch(Dispatchers.IO) {
                    val response = apiService.createNewUsersWithTypes(
                        userName = userName,
                        idType = idType,
                        description = description,
                        date = date,
                        totalMoney = totalMoney,
                    )
                }
                findNavController().navigate(R.id.action_addNewDetailReceiptBSFM_to_drawerReceipt)
                Toast.makeText(
                    requireContext(),
                    "OK",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancleDetail ->{
                Toast.makeText(
                    requireContext(),
                    "Cancle",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCalenderReceipt -> {
                initDataPicker()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initDataPicker() {
        val style = DatePickerDialog.THEME_HOLO_LIGHT
        val pickerDialog = DatePickerDialog(
            requireActivity(), style,
            OnDateSetListener { view, year, month, dayOfMonth ->
                fmAddNewReceiptBinding.acbCalenderReceipt.text =
                    "$year-" + (month + 1) + "-$dayOfMonth"
                if (TextUtils.isEmpty(fmAddNewReceiptBinding.acbCalenderReceipt.text)) {
                    Toast.makeText(
                        requireContext(),
                        "Please pick date and click ok to finish",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        pickerDialog.show()
    }


}