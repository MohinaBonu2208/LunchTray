package UI.order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.lunchtime.OrderViewModel
import com.bignerdranch.android.lunchtime.R
import com.bignerdranch.android.lunchtime.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentCheckoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCheckoutBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.entree.text = viewModel.entree.value?.name.toString()
        binding.side.text = viewModel.side.value?.name.toString()
        binding.accomp.text = viewModel.accompaniment.value?.name.toString()

        binding.entreePrice.text = viewModel.entree.value?.price.toString()
        binding.sidePrice.text = viewModel.side.value?.price.toString()
        binding.accompPrice.text = viewModel.accompaniment.value?.price.toString()
        binding.subtotal.text = getString(R.string.subtotal, viewModel.subtotal.value?.toString())
        binding.tax.text = getString(R.string.tax, viewModel.TAX.toString())
        binding.total.text = getString(R.string.total, viewModel.subtotal.value?.plus(viewModel.TAX).toString())

        binding.cancel.setOnClickListener { cancelOrder() }
        binding.send.setOnClickListener { sendOrder() }
    }

    private fun sendOrder() {
        val asd = getString(
            R.string.orderSend,
            viewModel.entree.value?.name.toString(),
            viewModel.entree.value?.price.toString(),
            viewModel.side.value?.name.toString(),
            viewModel.side.value?.price.toString(),
            viewModel.accompaniment.value?.name.toString(),
            viewModel.accompaniment.value?.price.toString(),
            viewModel.subtotal.value?.toString(),
            viewModel.TAX.toString(),
            viewModel.subtotal.value?.plus(viewModel.TAX).toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, "New Order!!")
            .putExtra(Intent.EXTRA_TEXT, asd)

        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }

    private fun cancelOrder() {
        findNavController().navigate(R.id.action_checkoutFragment_to_startOrderFragment)
        viewModel.resetOrder()
    }

}