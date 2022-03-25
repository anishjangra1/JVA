package com.jva.ui.login
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jva.MainActivity
import com.jva.R
import com.jva.databinding.SignupBinding
import com.jva.ui.viewmodels.JVMViewModel
import com.jva.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.login_signup.*

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignupBinding
    private val viewModel: JVMViewModel by viewModels()
    var spinner :String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  DataBindingUtil.setContentView(this, R.layout.signup)

        binding.nextButton.setOnClickListener(View.OnClickListener {
            validateData()
        })

        binding.typeOfCustomer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                if(position==0){
                    Toast.makeText(this@SignUpActivity, "Select Type", Toast.LENGTH_SHORT)
                        .show()
                }else{
                    spinner = "Selected"
                }
//                Toast.makeText(this@SignUpActivity, getString(R.string.selected_item) + " " +
//                            "" + typeofCus[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
     }


    private fun validateData() {

        binding.apply {
//            if (name.text?.length!! < 5) {
//                Toast.makeText(this@SignUpActivity, "Enter Name!", Toast.LENGTH_SHORT).show()
//            } else if (spinner?.length!! ==0) {
//                Toast.makeText(this@SignUpActivity, "Select Type of Organization!", Toast.LENGTH_SHORT)
//                    .show()
//            }else if (mobileNumber.text?.length!! < 5) {
//                Toast.makeText(this@SignUpActivity, "Enter your mobile number!", Toast.LENGTH_SHORT).show()
//            }else if (Utils.isValidEmail(email.text)) {
//                Toast.makeText(this@SignUpActivity, "Enter valid email!", Toast.LENGTH_SHORT).show()
//            }else if (password.text?.length!! < 5) {
//                Toast.makeText(this@SignUpActivity, "Password should have minimum 5 characters!", Toast.LENGTH_SHORT).show()
//            }else{
            showProgress(true)
                viewModel.registerUser(name.text!!, mobileNumber.text!!,email.text,spinner, password.text!!)

                viewModel.responseRegister.observe(this@SignUpActivity, Observer { it ->
                    it.data?.let {
                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    showProgress(false)
                })

//            }
        }
    }
    private fun showProgress(show: Boolean) {
        binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}