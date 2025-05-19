package com.vincnx.registration

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vincnx.registration.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    var fname: String? = null
    var username: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fname = intent.getStringExtra("key_fullname")
        username = intent.getStringExtra("key_username")
        password = intent.getStringExtra("key_password")

        binding.tfUsername.doAfterTextChanged { editable ->
            viewModel.setUsername(editable.toString())
            viewModel.validateUsername(editable.toString())
            binding.tfUsernameLayout.error = viewModel.usernameErr.value
            binding.tfUsernameLayout.isErrorEnabled = !viewModel.usernameErr.value.isNullOrEmpty()
        }

        binding.tfPwd.doAfterTextChanged { editable ->
            viewModel.setPwd(editable.toString())
            viewModel.validatePwd(editable.toString())
            binding.tfPwdLayout.error = viewModel.pwdErr.value
            binding.tfPwdLayout.isErrorEnabled = !viewModel.pwdErr.value.isNullOrEmpty()
        }

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

    }

    private fun handleLogin() {
//        validate input
        if (
            !viewModel.usernameErr.value.isNullOrEmpty() ||
            !viewModel.pwdErr.value.isNullOrEmpty() ||
            !isFieldValid()
        ) return
//        show dialog
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Register Success")
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
//        remove all input
        binding.tfUsername.text?.clear()
        binding.tfPwd.text?.clear()
    }

    private fun isFieldValid(): Boolean {
        return username == viewModel.username.value.toString() && password == viewModel.pwd.value.toString()
    }
}