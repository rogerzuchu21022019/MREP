package nam.zuchu.asm.fragments.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nam.zuchu.asm.databinding.FragmentIntroduceBinding

class IntroduceFM : Fragment() {
    lateinit var fmIntroduceBinding: FragmentIntroduceBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmIntroduceBinding = FragmentIntroduceBinding.inflate(layoutInflater)
        return fmIntroduceBinding.root
    }
}