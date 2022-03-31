package nam.zuchu.asm.fragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.viewpager.TabReceiptViewpagerAdapter
import nam.zuchu.asm.databinding.FragmentReceiptBinding
import nam.zuchu.asm.fragments.features.receipts.ListReceiptFM
import team.hacker.seace.fragments.intro.ContainerFM
import team.hacker.seace.fragments.intro.ContainerFMDirections

class ReceiptFM : Fragment() {
    lateinit var fmReceiptBinding: FragmentReceiptBinding
    lateinit var adapterReceipt: TabReceiptViewpagerAdapter
    lateinit var action: NavDirections

    companion object {
        private const val FRAGMENT_DETAIL_RECEIPT = 0
        const val FRAGMENT_RECEIPT = 1
        var mFragmentCurrent = FRAGMENT_DETAIL_RECEIPT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fmReceiptBinding = FragmentReceiptBinding.inflate(layoutInflater)
        initViewPager()
        initTabLayout()
        return fmReceiptBinding.root
    }

    private fun initTabLayout() {
        TabLayoutMediator(
            fmReceiptBinding.tablayout, fmReceiptBinding.vpReceipt
        ) { tab, position ->

            if (position == 0) {
                tab.text = "Detail of Receipts"
            } else if (position == 1) {
                tab.text = "Receipts"
            }
        }.attach()
    }


    private fun initViewPager() {
        adapterReceipt = TabReceiptViewpagerAdapter(this)
        fmReceiptBinding.vpReceipt.adapter = adapterReceipt


        // TODO:  Run UI and show toast of screen
//        fmReceiptBinding.vpReceipt.registerOnPageChangeCallback(object :
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//
//                if (fmReceiptBinding.vpReceipt.currentItem==0) {
//                    Toast.makeText(requireContext(), "Expenditure", Toast.LENGTH_SHORT).show()
//                } else if (fmReceiptBinding.vpReceipt.currentItem==1) {
//                        Toast.makeText(requireContext(), "Receipt FM", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        }
//
//        )
    }

}