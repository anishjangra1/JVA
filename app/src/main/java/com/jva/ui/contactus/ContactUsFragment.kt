package com.jva.ui.contactus

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jva.WebActivity
import com.jva.databinding.FragmentContactusBinding
import com.jva.databinding.FragmentGalleryBinding
import com.jva.ui.adapter.NewsAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsFragment : Fragment() {

    private val viewModel: JVMViewModel by viewModels() 
    private var _binding: FragmentContactusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { 
        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        val root: View = binding.root
        
        getData()
      
 
        return root
    }

    private fun getData() {
        showProgress(true)
        viewModel.getContactResponse()

        viewModel.responseContacts.observe(viewLifecycleOwner, Observer { it ->
            showProgress(false)
            it.data!!.data[0]?.let {

                binding.phone.setText(it.mobile)
                binding.email.setText(it.email)
                binding.address.setText(it.address)
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