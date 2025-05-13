package com.vincnx.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vincnx.registration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tfFname.doAfterTextChanged { editable ->
            viewModel.setFname(editable.toString())
            viewModel.validateFname(editable.toString())
            binding.tfFnameLayout.error = viewModel.fnameErr.value
            binding.tfFnameLayout.isErrorEnabled = !viewModel.fnameErr.value.isNullOrEmpty()
        }

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
            if (viewModel.pwdConf.value.toString().isNotEmpty()) {
                binding.tfPwdConfLayout.error = viewModel.pwdConfErr.value
                binding.tfPwdConfLayout.isErrorEnabled = !viewModel.pwdConfErr.value.isNullOrEmpty()
            }
        }

        binding.tfPwdConf.doAfterTextChanged { editable ->
            viewModel.setPwdConf(editable.toString())
            viewModel.validatePwdConf(editable.toString())
            binding.tfPwdConfLayout.error = viewModel.pwdConfErr.value
            binding.tfPwdConfLayout.isErrorEnabled = !viewModel.pwdConfErr.value.isNullOrEmpty()
        }

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {
//        validate input
        if (
            !viewModel.fnameErr.value.isNullOrEmpty() ||
            !viewModel.usernameErr.value.isNullOrEmpty() ||
            !viewModel.pwdErr.value.isNullOrEmpty() ||
            !viewModel.pwdConfErr.value.isNullOrEmpty()
        ) return
//        show dialog
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Register Success")
            setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled registration
                dialog.dismiss()
            }
        }.show()
//        remove all input
        binding.tfFname.text?.clear()
        binding.tfUsername.text?.clear()
        binding.tfPwd.text?.clear()
        binding.tfPwdConf.text?.clear()
        binding.tfFnameLayout.isErrorEnabled = false
        binding.tfUsernameLayout.isErrorEnabled = false
        binding.tfPwdLayout.isErrorEnabled = false
        binding.tfPwdConfLayout.isErrorEnabled = false
    }
}