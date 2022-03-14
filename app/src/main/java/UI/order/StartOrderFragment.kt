package UI.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.lunchtime.R
import com.bignerdranch.android.lunchtime.databinding.FragmentStartOrderBinding

class StartOrderFragment : Fragment() {

    private var _binding: FragmentStartOrderBinding? = null
    val binding get() = _binding!!

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val fragmentBinding = FragmentStartOrderBinding.inflate(inflater, container, false)
         _binding = fragmentBinding

         binding.startBtn.setOnClickListener { moveNext() }
        return fragmentBinding.root
    }

    private fun moveNext() {
        findNavController().navigate(R.id.action_startOrderFragment_to_entreeMenuFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}