package nam.zuchu.asm.fragments.crud

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentAddNewDetailExpenditureBsheetBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService
import java.util.*

@DelicateCoroutinesApi
class AddNewDetailExpenditureBSFM : BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var fmAddNewExpenditureBinding: FragmentAddNewDetailExpenditureBsheetBinding
    var calendar: Calendar = Calendar.getInstance()
    lateinit var snackbar: Snackbar

    var idType: Int? = null

    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmAddNewExpenditureBinding =
            FragmentAddNewDetailExpenditureBsheetBinding.inflate(layoutInflater)
        getDataFromAPI()
        initClick()
        return fmAddNewExpenditureBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            val responseUsers = apiService.getAllUsers()
            val responseTypes = apiService.getTypeByStatus("Chi")
            if (responseUsers.isSuccessful && responseTypes.isSuccessful) {
                for (data in 0 until responseUsers.body()!!.size) {
                    val userArr = arrayOfNulls<String>(responseUsers.body()!!.size)
                    for (data1 in 0 until responseUsers.body()!!.size) {
                        userArr[data1] = responseUsers.body()!![data1].userName
                        val arrayAdapterUserName = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            userArr
                        )
                        fmAddNewExpenditureBinding.autoCompleteUserName.setAdapter(
                            arrayAdapterUserName
                        )
                    }
                }
                for (data in 0 until responseTypes.body()!!.size) {
                    val typeArr = arrayOfNulls<String>(responseTypes.body()!!.size)
                    for (data1 in 0 until responseTypes.body()!!.size) {
                        if (responseTypes.body()!![data1].status == "Chi" && (responseTypes.body()!![data1].typeOfName != "")) {
                            typeArr[data1] = responseTypes.body()!![data1].typeOfName

                            val arrayAdapterID = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_expandable_list_item_1,
                                typeArr
                            )
                            fmAddNewExpenditureBinding.autoCompleteID.setAdapter(arrayAdapterID)
                            fmAddNewExpenditureBinding.autoCompleteID.onItemClickListener =
                                AdapterView.OnItemClickListener { _, _, i, _ ->
                                    idType = responseTypes.body()!![i].idType
                                    fmAddNewExpenditureBinding.tvID.text = idType.toString()
                                }

                        }


                    }
                }
            }

        }
    }

    private fun initClick() {
        fmAddNewExpenditureBinding.acbCancleDetail.setOnClickListener(this)
        fmAddNewExpenditureBinding.acbOkDoneDetail.setOnClickListener(this)
        fmAddNewExpenditureBinding.acbCalender.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.acbOkDoneDetail -> {
                val date: String = fmAddNewExpenditureBinding.acbCalender.text.toString().trim()
                val description: String =
                    fmAddNewExpenditureBinding.tieDescription.text.toString().trim()
                val totalMoney: Double =
                    fmAddNewExpenditureBinding.tieTotal.text.toString().toDouble()
                val idType: Int = fmAddNewExpenditureBinding.tvID.text.toString().toInt()
                val userName: String =
                    fmAddNewExpenditureBinding.autoCompleteUserName.text.toString().trim()

                GlobalScope.launch(Dispatchers.IO) {
                    val response = apiService.createNewUsersWithTypes(
                        date = date,
                        description = description,
                        totalMoney = totalMoney,
                        idType = idType,
                        userName = userName
                    )
                }
                findNavController().navigate(R.id.action_addNewDetailExpenditureBSFM_to_drawerExpenditure)
                Toast.makeText(
                    requireContext(),
                    "Go to destination successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancleDetail -> Toast.makeText(
                requireContext(),
                "Cancle",
                Toast.LENGTH_SHORT
            ).show()
            R.id.acbCalender -> {
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
                fmAddNewExpenditureBinding.acbCalender.text =
                    "$year-" + (month + 1) + "-$dayOfMonth"
                if (TextUtils.isEmpty(fmAddNewExpenditureBinding.acbCalender.text)) {
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