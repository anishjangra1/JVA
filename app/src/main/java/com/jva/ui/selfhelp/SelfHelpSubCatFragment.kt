package com.jva.ui.selfhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.jva.R
import com.jva.databinding.FragmentSelfhelpBinding
import com.jva.ui.adapter.SelfSubHelpAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfHelpSubCatFragment : Fragment() {

    private val viewModel: JVMViewModel by viewModels()
    private var _binding: FragmentSelfhelpBinding? = null
    val args :SelfHelpAllCatFragmentArgs by navArgs()

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
        viewModel.getSelfSubHelpResponse(args.categoryId)

        viewModel.responseSelfSubHelp.observe(viewLifecycleOwner, Observer {
            showProgress(false)

            it?.let {
                binding.rvSelfHelp.adapter = SelfSubHelpAdapter(it.data!!.data) {
                    val action = SelfHelpSubCatFragmentDirections.actionNavSelfSubHelpToSelfHelpAllCatFragment(it.category_id.toInt(),it.sub_category_id.toInt())
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