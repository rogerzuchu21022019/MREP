package team.hacker.seace.fragments.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nam.zuchu.asm.databinding.FragmentIntro2FmBinding

class Intro2FM : Fragment() {
    lateinit var fmIntro2FmBinding: FragmentIntro2FmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmIntro2FmBinding = FragmentIntro2FmBinding.inflate(layoutInflater)
        return fmIntro2FmBinding.root
    }
}