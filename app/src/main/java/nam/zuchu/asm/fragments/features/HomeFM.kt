package nam.zuchu.asm.fragments.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nam.zuchu.asm.adapters.viewpager.TabReceiptViewpagerAdapter
import nam.zuchu.asm.databinding.FragmentHomeBinding

class HomeFM : Fragment() {
    lateinit var fmHomeBinding: FragmentHomeBinding
    lateinit var adapterReceipt: TabReceiptViewpagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

        return fmHomeBinding.root
    }


}