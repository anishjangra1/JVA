//package com.jva.ui.main.view
//
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.jva.R
//import com.jva.data.model.User
//import com.jva.databinding.SampleActivityBinding
//import com.jva.ui.main.adapter.MainAdapter
//import com.jva.ui.main.viewmodel.MainViewModel
//import com.jva.utils.Status.ERROR
//import com.jva.utils.Status.LOADING
//import com.jva.utils.Status.SUCCESS
//
//
//class SampleMainActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: MainViewModel
//    private lateinit var adapter: MainAdapter
//    lateinit var binding: SampleActivityBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.sample_activity)
//
//        setupViewModel()
//        setupUI()
//        setupObservers()
//    }
//
//
//    private fun setupViewModel() {
//        viewModel = ViewModelProviders.of( this,
//            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
//        )[MainViewModel::class.java]
//    }
//
//    private fun setupUI() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = MainAdapter(arrayListOf())
//        binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                binding.recyclerView.context,
//                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        binding.recyclerView.adapter = adapter
//    }
//
//    private fun setupObservers() {
//        viewModel.getUsers().observe(this, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                     SUCCESS -> {
//                        binding.recyclerView.visibility = View.VISIBLE
//                        binding. progressBar.visibility = View.GONE
////                        resource.data?.let { user -> retrieveList(user) }
//                    }
//                     ERROR -> {
//                        binding. recyclerView.visibility = View.VISIBLE
//                        binding. progressBar.visibility = View.GONE
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                     LOADING -> {
//                        binding. progressBar.visibility = View.VISIBLE
//                        binding.recyclerView.visibility = View.GONE
//                    }
//                }
//            }
//        })
//    }
//
//    private fun retrieveList(users: List<User>) {
//        adapter.apply {
////            addUsers(users)
//            notifyDataSetChanged()
//        }
//    }
//}
