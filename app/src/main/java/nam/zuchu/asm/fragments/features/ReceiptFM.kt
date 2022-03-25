package nam.zuchu.asm.fragments.features
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import nam.zuchu.asm.adapters.viewpager.TabReceiptViewpagerAdapter
import nam.zuchu.asm.databinding.FragmentReceiptBinding

class ReceiptFM : Fragment(){
    lateinit var fmReceiptBinding: FragmentReceiptBinding
    lateinit var adapterReceipt: TabReceiptViewpagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmReceiptBinding = FragmentReceiptBinding.inflate(layoutInflater)
        initViewPager()
        initTabLayout()
        return fmReceiptBinding.root
    }
    private fun initTabLayout() {
        TabLayoutMediator(
            fmReceiptBinding.tablayout,fmReceiptBinding.vpReceipt
        ) { tab, position ->

            if (position == 0) {
                tab.text = "Kind of Receipt"
            } else if (position == 1) {
                tab.text = "Receipt"
            }
        }.attach()
    }


    private fun initViewPager() {
        adapterReceipt = TabReceiptViewpagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        fmReceiptBinding.vpReceipt.adapter = adapterReceipt
    }

}