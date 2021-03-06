package nam.zuchu.asm.fragments.features.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.activities.DrawerLayoutActivity
import nam.zuchu.asm.databinding.FragmentLoginBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService
import java.lang.Exception

@DelicateCoroutinesApi
class LoginFM : Fragment(), View.OnClickListener {
    var fmLogin: FragmentLoginBinding? = null
    var navController: NavController? = null
    var auth: FirebaseAuth? = null
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmLogin = FragmentLoginBinding.inflate(layoutInflater)
        getDataFromAPI()
        initButton()
        initNavController()
        initFBAuth()
        hideKeyBoard()
        return fmLogin!!.root
    }

    private fun getDataFromAPI() {

    }

    private fun initNavController() {
        navController = findNavController()
    }


    private fun initButton() {
        fmLogin!!.acbLogin.setOnClickListener(this)
        fmLogin!!.imgDelete.setOnClickListener(this)
        fmLogin!!.tvForgetPassword.setOnClickListener(this)
        fmLogin!!.tvRegister.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.imgDelete) {
            fmLogin!!.tiePassWord.setText("")
        }
        if (id == R.id.acbLogin) {
            initLogin()
        }
        if (id == R.id.tvRegister) {
            val action: NavDirections = LoginFMDirections.actionLoginFMToSignUpFM()
            navController!!.navigate(action)
        }
        if (id == R.id.tvForgetPassword) {
            val action: NavDirections = LoginFMDirections.actionLoginFMToForgetPasswordFM()
            navController!!.navigate(action)
        }
    }

    private fun initFBAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun initLogin() {
        val fbEmail: String = fmLogin!!.tieEmailLogin.text.toString().trim()
        val fbPassword: String = fmLogin!!.tiePassWord.text.toString().trim()
        if (TextUtils.isEmpty(fbEmail) || TextUtils.isEmpty(fbPassword)) {
            Toast.makeText(
                requireContext(),
                "Please press your email and pasword",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        auth = FirebaseAuth.getInstance()

        auth!!.signInWithEmailAndPassword(fbEmail, fbPassword)
            .addOnCompleteListener(requireActivity()){
                if (it.isSuccessful) {
                    Toast.makeText(requireContext().applicationContext,"Login Succesfully.",Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireActivity(), DrawerLayoutActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext().applicationContext, "Login failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fmLogin = null
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