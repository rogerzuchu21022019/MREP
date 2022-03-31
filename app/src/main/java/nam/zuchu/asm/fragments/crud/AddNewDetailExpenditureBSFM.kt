package nam.zuchu.asm.fragments.crud

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
            val responeUsers = apiService.getAllUsers()
            val responeTypes = apiService.getAllTypes()
            if (responeUsers.isSuccessful && responeTypes.isSuccessful) {
                for (data in 0 until responeUsers.body()!!.size) {
                    val userArr = arrayOfNulls<String>(responeUsers.body()!!.size)
                    for (data1 in 0 until responeUsers.body()!!.size) {
                        userArr[data1] = responeUsers.body()!![data1].userName
                        val arrayAdapterUserName = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            userArr
                        )
                        fmAddNewExpenditureBinding.autoCompleteUserName.setAdapter(arrayAdapterUserName)
                    }
                }
                for (data in 0 until responeTypes.body()!!.size) {
                    val typeArr = arrayOfNulls<Int>(responeTypes.body()!!.size)
                    for (data1 in 0 until responeTypes.body()!!.size) {
                        typeArr[data1] = responeTypes.body()!![data1].idType
                        val arrayAdapterID = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            typeArr
                        )
                        fmAddNewExpenditureBinding.autoCompleteID.setAdapter(arrayAdapterID)
                    }
                }
            }

        }
    }

    private fun initClick() {
        fmAddNewExpenditureBinding.acbCancleDetail.setOnClickListener(this)
        fmAddNewExpenditureBinding.acbOkDetail.setOnClickListener(this)
        fmAddNewExpenditureBinding.acbCalender.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.acbOkExpenditure -> {

                findNavController().navigate(R.id.action_addNewDetailExpenditureBSFM_to_drawerExpenditure)
                Toast.makeText(
                    requireContext(),
                    "Go to destination successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.acbCancleExpenditure -> Toast.makeText(
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
                    " $dayOfMonth/" + (month + 1) + "/$year"
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