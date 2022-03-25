package com.jva.ui.selfhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.jva.R
import com.jva.databinding.FragmentSelfhelpBinding
import com.jva.ui.adapter.SelfHepAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelfHelpFragment : Fragment() {

    private val viewModel: JVMViewModel by viewModels()
    private var _binding: FragmentSelfhelpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelfhelpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getData(root)

        return root
    }

    private fun getData(root: View) {
        showProgress(true)
        viewModel.getSelfHelpResponse()
        binding.rvSelfHelp.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.responseSelfHelp.observe(viewLifecycleOwner, Observer {
            showProgress(false)

            it?.let {

                binding.rvSelfHelp.adapter = SelfHepAdapter(it.data!!.data) {
                  val action=  SelfHelpFragmentDirections.actionNavSelfHelpToSelfHelpSubCatFragment2(it.category_id.toInt())
                    Navigation.findNavController(root).navigate(action)
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