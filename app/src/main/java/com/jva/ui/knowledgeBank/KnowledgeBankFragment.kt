package com.jva.ui.knowledgeBank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jva.databinding.FragmentKnowledgebankBinding
import com.jva.ui.adapter.KnowledgeCenterAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KnowledgeBankFragment : Fragment() {

    private val viewModel: JVMViewModel  by viewModels()
    private var _binding: FragmentKnowledgebankBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentKnowledgebankBinding.inflate(inflater, container, false)
        val root: View = binding.root

        showProgress(true)
        viewModel.fetchKnowledgeCenterResponse()

        viewModel.responseKnowledge.observe(viewLifecycleOwner, Observer {
            showProgress(false)

            it?.let {

                binding.rvCategories.adapter = KnowledgeCenterAdapter(it.data!!.data) {

                }
            }

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showProgress(show: Boolean) {
        _binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}