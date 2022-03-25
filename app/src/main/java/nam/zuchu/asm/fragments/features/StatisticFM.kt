package nam.zuchu.asm.fragments.features
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nam.zuchu.asm.databinding.FragmentStatisticBinding

class StatisticFM : Fragment() {
    lateinit var fmStatisticBinding: FragmentStatisticBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmStatisticBinding = FragmentStatisticBinding.inflate(layoutInflater)
        return fmStatisticBinding.root
    }
}