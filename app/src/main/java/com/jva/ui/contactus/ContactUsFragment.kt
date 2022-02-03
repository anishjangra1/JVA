package com.jva.ui.contactus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jva.databinding.FragmentContactusBinding
import com.jva.databinding.FragmentGalleryBinding

class ContactUsFragment : Fragment() {

    private lateinit var viewModel: ContactUsViewModel
    private var _binding: FragmentContactusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =  ViewModelProvider(this)?.get(ContactUsViewModel::class.java)

        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textContactus
//        viewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}