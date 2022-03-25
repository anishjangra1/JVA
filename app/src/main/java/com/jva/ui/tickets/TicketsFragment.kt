package com.jva.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jva.databinding.FragmentTicketsBinding
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketsFragment : Fragment() {

    private val viewModel: JVMViewModel by viewModels()
    private var _binding: FragmentTicketsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.nextButton.setOnClickListener(View.OnClickListener {
            if(binding.ticketET.text.length>3){
                sendTicketData(binding.ticketET.text.toString())
            }else{
                Toast.makeText(requireContext(),"Please write some query!", Toast.LENGTH_LONG).show()
            }

        })


        return root
    }

    private fun sendTicketData(query: String) {
        showProgress(true)
        viewModel.sendTicketResponse(query)

        viewModel.responseTicket.observe(viewLifecycleOwner, Observer {
            showProgress(false)

            it?.let {
                Toast.makeText(requireContext(),it.data?.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showProgress(show: Boolean) {
        _binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}