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
import com.bignerdranch.android.lunchtime.databinding.FragmentEntreeMenuBinding

class EntreeMenuFragment : Fragment() {

    val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentEntreeMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntreeMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingTexts()

        binding.next.setOnClickListener { goToNext() }
        binding.cancel.setOnClickListener { cancel() }


        viewModel.subtotal.observe(viewLifecycleOwner) { newPrice ->
            binding.subtotal.text = getString(R.string.price, newPrice.toString())
        }

        binding.entreeOptions.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.cauliflower -> viewModel.setEntree("cauliflower")
                R.id.chili -> viewModel.setEntree("chili")
                R.id.pasta -> viewModel.setEntree("pasta")
                R.id.skillet -> viewModel.setEntree("skillet")
            }
        }
    }


    private fun goToNext() {
        findNavController().navigate(R.id.action_entreeMenuFragment_to_sideMenuFragment)
    }

    private fun cancel() {
        findNavController().navigate(R.id.action_entreeMenuFragment_to_startOrderFragment)
        viewModel.resetOrder()
    }

    private fun bindingTexts() {
        binding.cauliflower.text = viewModel.menuItems["cauliflower"]?.name.toString()
        binding.cauliflowerDescription.text =
            viewModel.menuItems["cauliflower"]?.description.toString()
        binding.cauliflowerPrice.text =
            getString(R.string.price, viewModel.menuItems["cauliflower"]?.price.toString())

        binding.chili.text = viewModel.menuItems["chili"]?.name.toString()
        binding.chiliDescription.text = viewModel.menuItems["chili"]?.description.toString()
        binding.chiliPrice.text =
            getString(R.string.price, viewModel.menuItems["chili"]?.price.toString())

        binding.pasta.text = viewModel.menuItems["pasta"]?.name.toString()
        binding.pastaDescription.text = viewModel.menuItems["pasta"]?.description.toString()
        binding.pastaPrice.text =
            getString(R.string.price, viewModel.menuItems["pasta"]?.price.toString())

        binding.skillet.text = viewModel.menuItems["skillet"]?.name.toString()
        binding.skilletDescription.text = viewModel.menuItems["skillet"]?.description.toString()
        binding.skilletPrice.text =
            getString(R.string.price, viewModel.menuItems["skillet"]?.price.toString())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}