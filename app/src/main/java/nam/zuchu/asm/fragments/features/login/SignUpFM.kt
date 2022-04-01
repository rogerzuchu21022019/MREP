package nam.zuchu.asm.fragments.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.databinding.FragmentSignupBinding
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService

class SignUpFM : Fragment() {
    lateinit var fmSignUpBinding: FragmentSignupBinding
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmSignUpBinding = FragmentSignupBinding.inflate(layoutInflater)
        initButton()
        initNavController()
        return fmSignUpBinding.root
    }

    private fun initNavController() {
        navController = findNavController()
    }

    private fun initButton() {
        fmSignUpBinding.lavAdd.setOnClickListener {
            var user = UsersItem(
                emptyList(),
                userName = fmSignUpBinding.tieUserName.text.toString(),
                fullName = fmSignUpBinding.tieFullName.text.toString(),
                password = fmSignUpBinding.tiePassword.text.toString(),
                phone = fmSignUpBinding.tiePhone.text.toString(),
                age = fmSignUpBinding.tieAge.text.toString().toInt(),
                email = fmSignUpBinding.tieEmail.text.toString(),
                avartar = ""
            )
            GlobalScope.launch(Dispatchers.IO) {
                val response = apiService.createNewUser(
                    user.userName,
                    user.age,
                    user.email,
                    user.avartar,
                    user.password,
                    user.phone,
                    user.fullName
                )
//                if (response.email.equals()){
//                    for (data in response.toString()){
//                        Log.d("CREATE",data.toString())
//                    }
//                }else{
//                    Log.d("Fail","Fail")
//
//                }
            }
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(fmSignUpBinding.tieEmail.text.toString(), fmSignUpBinding.tiePassword.text.toString())
                .addOnCompleteListener(
                    requireActivity()
                ) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Register User Successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val action: NavDirections = SignUpFMDirections.actionSignUpFMToLoginFM()
                        navController.navigate(action)
                    } else {
//
                        Toast.makeText(
                            requireContext(), "Register failed. Email Existed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }

    }
}