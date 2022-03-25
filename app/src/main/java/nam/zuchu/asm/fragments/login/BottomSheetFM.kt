package nam.zuchu.asm.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentBottomSheetBinding

class BottomSheetFM : BottomSheetDialogFragment(), View.OnClickListener {
    var fmBottomSheetBinding: FragmentBottomSheetBinding? = null
    var navController: NavController? = null
    lateinit var dialog:BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmBottomSheetBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
        initButton()
        initNavController()
        return fmBottomSheetBinding!!.root
    }


    private fun initNavController() {
        navController =findNavController()
    }

    private fun initButton() {
        fmBottomSheetBinding!!.acbSignOutNo.setOnClickListener(this)
        fmBottomSheetBinding!!.acbSignOutYes.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.acbSignOutYes) {
            Toast.makeText(requireContext(), "ok", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
            FirebaseAuth.getInstance().signOut()
        }
        if (id == R.id.acbSignOutNo) {
            val action: NavDirections = BottomSheetFMDirections.actionDrawerExitToDrawerHome2()
            navController!!.navigate(action)
        }
    }
}