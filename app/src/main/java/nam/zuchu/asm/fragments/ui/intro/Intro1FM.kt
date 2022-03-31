package nam.zuchu.asm.fragments.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nam.zuchu.asm.databinding.FragmentIntro1FmBinding

class Intro1FM : Fragment() {
    lateinit var intro1FmBinding : FragmentIntro1FmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        intro1FmBinding = FragmentIntro1FmBinding.inflate(layoutInflater)
        return  intro1FmBinding.root
    }
}