package com.jva.ui.tickets

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
import com.jva.databinding.FragmentKnowledgebankBinding
import com.jva.databinding.FragmentTicketsBinding

class TicketsFragment : Fragment() {

    private lateinit var viewModel: TicketsViewModel
    private var _binding: FragmentTicketsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =  ViewModelProvider(this)?.get(TicketsViewModel::class.java)

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
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