package nam.zuchu.asm.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import nam.zuchu.asm.fragments.features.receipts.DetailReceiptFM
import nam.zuchu.asm.fragments.features.receipts.ListReceiptFM

class TabReceiptViewpagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailReceiptFM()
            1 -> ListReceiptFM()
            else -> DetailReceiptFM()
        }

    }

}