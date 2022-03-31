package nam.zuchu.asm.fragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import nam.zuchu.asm.adapters.viewpager.TabExpenditureViewpagerAdapter
import nam.zuchu.asm.databinding.FragmentExpenditureBinding

class ExpenditureFM : Fragment() {
    lateinit var fmExpenditureBinding: FragmentExpenditureBinding
    lateinit var adapter: TabExpenditureViewpagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmExpenditureBinding = FragmentExpenditureBinding.inflate(layoutInflater)
        initViewPager()
        initTabLayout()
        return fmExpenditureBinding.root
    }

    private fun initTabLayout() {
        TabLayoutMediator(
            fmExpenditureBinding.tablayout,
            fmExpenditureBinding.vpExpenditure
        ) { tab, position ->

            if (position == 0) {
                tab.text = "Detail of Expenditure"
            } else if (position == 1) {
                tab.text = "Expenditures"
            }
        }.attach()
    }

    private fun initViewPager() {
        adapter = TabExpenditureViewpagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycler = lifecycle
        )
        fmExpenditureBinding.vpExpenditure.adapter = adapter
    }

}