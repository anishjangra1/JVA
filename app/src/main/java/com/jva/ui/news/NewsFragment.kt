package com.jva.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jva.MainActivity
import com.jva.WebActivity
import com.jva.databinding.FragmentNewsBinding
import com.jva.ui.adapter.NewsAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: JVMViewModel by viewModels()
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)


        showProgress(true)
        viewModel.getNewsResponse()

        viewModel.responseNews.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            it?.let {

                binding.rvNews.adapter = NewsAdapter(it.data!!.data) {
                    val intent = Intent(requireActivity(), WebActivity::class.java)
                    intent.putExtra("news",it.link)
                    requireActivity().startActivity(intent)
                }
            }

        })
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showProgress(show: Boolean) {
        _binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}