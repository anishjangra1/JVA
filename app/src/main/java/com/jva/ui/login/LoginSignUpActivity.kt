package com.jva.ui.login

import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jva.MainActivity
import com.jva.R
import com.jva.databinding.LoginSignupBinding


class LoginSignUpActivity : AppCompatActivity() {

    private lateinit var binding: LoginSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  DataBindingUtil.setContentView(this, R.layout.login_signup)

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
                Toast.makeText(this@LoginSignUpActivity, "Enter Id", Toast.LENGTH_SHORT).show()
            } else if (password.text?.length!! < 3) {
                Toast.makeText(this@LoginSignUpActivity, "Enter Password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this@LoginSignUpActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}