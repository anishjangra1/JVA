package com.jva.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jva.MainActivity
import com.jva.R
import com.jva.databinding.ActivityLoginBinding
import com.jva.databinding.LoginSignupBinding
import com.jva.ui.viewmodels.JVMViewModel
import com.jva.utils.AppPreferences
import com.jva.utils.AppPreferences.Companion.USER_ID
import com.jva.utils.AppPreferences.Companion.USER_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var appPreferences: AppPreferences
    private val viewModel: JVMViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signup.setOnClickListener(View.OnClickListener {


            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        })
        binding.continueButton.setOnClickListener(View.OnClickListener {
            validateData()
        })
    }

    private fun validateData() {

        binding.apply {
            if (email.text?.length!! < 5) {
                Toast.makeText(this@LoginActivity, "Enter Id", Toast.LENGTH_SHORT).show()
            } else if (password.text?.length!! < 3) {
                Toast.makeText(this@LoginActivity, "Enter Password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                showProgress(true)
                viewModel.fetchLoginDetailsResponse(email.text.toString(), password.text.toString())
                viewModel.responseLogin.observe(this@LoginActivity, Observer { it ->
                    showProgress(false)
                    it.data?.let {
                        appPreferences.saveUserType(it.login_type)
                        appPreferences.saveUserData(USER_NAME, it.data.name)
                        appPreferences.saveUserData(USER_ID, it.data.user_id)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                })


            }
        }
    }

    private fun showProgress(show: Boolean) {
        binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}