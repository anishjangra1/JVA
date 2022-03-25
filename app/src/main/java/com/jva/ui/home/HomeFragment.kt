package com.jva.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jva.R
import com.jva.databinding.FragmentHomeBinding
import com.jva.ui.adapter.ServicesAdapter
import com.jva.ui.viewmodels.JVMViewModel
import com.jva.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var appPreferences: AppPreferences
    private val viewModel: JVMViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvusername.setText("Welcome ${appPreferences.getUserName().toString()}")
        getData(root)
        return root
    }

    private fun getData(root: View) {
        showProgress(true)
        viewModel.getServicesResponse()

        binding.rvServices.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.responseService.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            it.data?.let {
                binding.rvServices.adapter = ServicesAdapter(requireContext(),it.data) {
                    Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_contact_us);
                }
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