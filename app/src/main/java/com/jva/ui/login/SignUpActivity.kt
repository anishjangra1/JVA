package com.jva.ui.login
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jva.MainActivity
import com.jva.R
import com.jva.databinding.SignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: SignupBinding
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
            if (name.text?.length!! < 5) {
                Toast.makeText(this@SignUpActivity, "Enter Name!", Toast.LENGTH_SHORT).show()
            } else if (spinner?.length!! ==0) {
                Toast.makeText(this@SignUpActivity, "Select Type of Organization!", Toast.LENGTH_SHORT)
                    .show()
            }else if (mobileNumber.text?.length!! < 5) {
                Toast.makeText(this@SignUpActivity, "Enter your mobile number!", Toast.LENGTH_SHORT).show()
            }else if (otp.text?.length!! < 3) {
                Toast.makeText(this@SignUpActivity, "Enter valid otp!", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}