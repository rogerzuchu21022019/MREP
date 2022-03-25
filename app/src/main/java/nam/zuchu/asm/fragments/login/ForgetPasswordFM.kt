package nam.zuchu.asm.fragments.login

import androidx.navigation.Navigation.findNavController
import android.view.animation.Animation
import androidx.navigation.NavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener
import androidx.navigation.NavDirections
import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import nam.zuchu.asm.R
import nam.zuchu.asm.databinding.FragmentForgetPasswordBinding
import java.lang.Exception

class ForgetPasswordFM : Fragment(), View.OnClickListener {
    var fmForgetBinding: FragmentForgetPasswordBinding? = null
    private var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmForgetBinding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        initNavController()
        initButton()
        hideKeyBoard()
        return fmForgetBinding!!.root
    }

    private fun initNavController() {
        navController =requireActivity().findNavController(R.id.fmNavGraph1)
    }


    private fun initButton() {
        fmForgetBinding!!.acbReset.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fmForgetBinding = null
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.acbReset) {
            //TODO SOMETHING
            val email: String = fmForgetBinding!!.tieResetEmail.getText().toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(requireContext(), "Email is not empty", Toast.LENGTH_LONG).show()
                return
            }
            val auth = FirebaseAuth.getInstance()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        val action: NavDirections =ForgetPasswordFMDirections.actionForgetPasswordFMToLoginFM()
                        navController!!.navigate(action)
                        Toast.makeText(requireContext(), "Please check your email", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun hideKeyBoard() {
        try {
            val inputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                0
            )
            inputMethodManager.currentInputMethodSubtype
        } catch (ignored: Exception) {
        }
    }
}