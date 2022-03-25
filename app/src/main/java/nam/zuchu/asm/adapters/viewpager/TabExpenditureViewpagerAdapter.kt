package nam.zuchu.asm.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import nam.zuchu.asm.fragments.features.*

class TabExpenditureViewpagerAdapter(fragmentManager: FragmentManager, lifecycler: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycler) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {

            1 -> {
                TypesOfNameFM()
            }
            2 -> {
                ExpenditureFM()
            }
            else -> {
                return TypesOfNameFM()
            }
        }

    }

}