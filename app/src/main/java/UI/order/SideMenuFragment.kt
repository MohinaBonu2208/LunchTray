package UI.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.lunchtime.OrderViewModel
import com.bignerdranch.android.lunchtime.R
import com.bignerdranch.android.lunchtime.databinding.FragmentSideMenuBinding
import kotlinx.coroutines.NonCancellable.cancel

class SideMenuFragment : Fragment() {

    val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentSideMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSideMenuBinding.inflate(inflater, container, false)
        _binding =fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingTexts()

       viewModel.subtotal.observe(viewLifecycleOwner) { newPrice ->
           binding.subtotal.text = getString(R.string.subtotal, newPrice.toString())
       }
        binding.sideOptions.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.salad_name -> viewModel.setSide("salad")
                R.id.soup_name -> viewModel.setSide("soup")
                R.id.potatoes_name -> viewModel.setSide("potatoes")
                R.id.rice_name -> viewModel.setSide("rice")
            }
        }

        binding.next.setOnClickListener { goToNext() }
        binding.cancel.setOnClickListener { cancelOrder() }

    }

    private fun cancelOrder() {
         findNavController().navigate(R.id.action_sideMenuFragment_to_startOrderFragment)
        viewModel.resetOrder()
    }

    private fun bindingTexts() {
        binding.saladName.text = viewModel.menuItems["salad"]?.name
        binding.saladDescription.text = viewModel.menuItems["salad"]?.description
        binding.saladPrice.text = getString(R.string.price, viewModel.menuItems["salad"]?.price.toString())

        binding.soupName.text = viewModel.menuItems["soup"]?.name
        binding.soupDescription.text = viewModel.menuItems["soup"]?.description
        binding.soupPrice.text = getString(R.string.price, viewModel.menuItems["soup"]?.price.toString())

        binding.potatoesName.text = viewModel.menuItems["potatoes"]?.name
        binding.potatoesDescription.text = viewModel.menuItems["potatoes"]?.description
        binding.potatoesPrice.text = getString(R.string.price, viewModel.menuItems["potatoes"]?.price.toString())

        binding.riceName.text = viewModel.menuItems["rice"]?.name
        binding.riceDescription.text = viewModel.menuItems["rice"]?.description
        binding.ricePrice.text = getString(R.string.price, viewModel.menuItems["rice"]?.price.toString())
    }

    private fun goToNext() {
        findNavController().navigate(R.id.action_sideMenuFragment_to_accompanimentMenuFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}