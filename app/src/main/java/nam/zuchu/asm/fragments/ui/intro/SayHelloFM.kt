package team.hacker.seace.fragments.intro

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import nam.zuchu.asm.databinding.FragmentSayHelloFmBinding

class SayHelloFM : Fragment() {
    lateinit var fmSayHelloFmBinding: FragmentSayHelloFmBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()


        var handler : Handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
               var action : NavDirections = SayHelloFMDirections.actionSayHelloFMToContainerFM()
                navController.navigate(action)
        },3000)


        fmSayHelloFmBinding = FragmentSayHelloFmBinding.inflate(layoutInflater)
        return fmSayHelloFmBinding.root
    }
}