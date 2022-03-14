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
import com.bignerdranch.android.lunchtime.databinding.FragmentAccompanimentMenuBinding


class AccompanimentMenuFragment : Fragment() {

    val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentAccompanimentMenuBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentAccompanimentMenuBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener { goToNext() }
        binding.cancel.setOnClickListener { cancelOrder() }
        viewModel.subtotal.observe(viewLifecycleOwner) { newPrice ->
            binding.subTotal.text = getString(R.string.subtotal, newPrice.toString())
        }
        binding.options.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.bread -> viewModel.setAccompaniment("bread")
                R.id.berries -> viewModel.setAccompaniment("berries")
                R.id.pickles -> viewModel.setAccompaniment("pickles")
            }
        }

        bindingTexts()

    }

    private fun cancelOrder() {
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_startOrderFragment)
        viewModel.resetOrder()
    }

    private fun bindingTexts() {
        binding.bread.text = viewModel.menuItems["bread"]?.name
        binding.breadDescription.text = viewModel.menuItems["bread"]?.description
        binding.breadPrice.text =
            getString(R.string.price, viewModel.menuItems["bread"]?.price.toString())

        binding.berries.text = viewModel.menuItems["berries"]?.name
        binding.berriesDescription.text = viewModel.menuItems["berries"]?.description
        binding.berriesPrice2.text =
            getString(R.string.price, viewModel.menuItems["berries"]?.price.toString())

        binding.pickles.text = viewModel.menuItems["pickles"]?.name
        binding.picklesDescription.text = viewModel.menuItems["pickles"]?.description
        binding.picklesPrice.text =
            getString(R.string.price, viewModel.menuItems["pickles"]?.price.toString())


    }

    private fun goToNext() {
        findNavController().navigate(R.id.action_accompanimentMenuFragment_to_checkoutFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}