package com.vincnx.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val tfUsernameL by lazy {findViewById<TextInputLayout>(R.id.tf_username_layout)}
    private val tfUsername by lazy {findViewById<EditText>(R.id.tf_username)}
    private val tfPwdL by lazy {findViewById<TextInputLayout>(R.id.tf_pwd_layout)}
    private val tfPwd by lazy {findViewById<EditText>(R.id.tf_pwd)}
    private val tfPwdConfL by lazy {findViewById<TextInputLayout>(R.id.tf_pwd_conf_layout)}
    private val tfPwdConf by lazy {findViewById<EditText>(R.id.tf_pwd_conf)}
    private val btnRegister by lazy {findViewById<Button>(R.id.btn_register)}
    private var usernameErr: String? = null
    private var pwdErr: String? = null
    private var pwdConfErr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tfUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                usernameErr = null
                if (s.isNullOrEmpty()) {
                    usernameErr = "Field is required"
                } else if (s.length < 5) {
                    usernameErr = "Must be at least 5 characters"
                }
                tfUsernameL.error = usernameErr
                tfUsernameL.isErrorEnabled = usernameErr != null
            }
        })

        tfPwd.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                pwdErr = null
                if (s.isNullOrEmpty()) {
                    pwdErr = "Field is required"
                } else if (s.length < 6) {
                    pwdErr = "Must be at least 6 characters"
                } else if (s.length > 12) {
                    pwdErr = "Maximum 12 character"
                } else if (tfPwdConf.text.toString().isNotEmpty()) {
                    pwdConfErr = if (s.toString() != tfPwdConf.text.toString()) {
                        "Confirmation password not match"
                    } else {
                        null
                    }
                }
                tfPwdL.error = pwdErr
                tfPwdConfL.error = pwdConfErr
                tfPwdL.isErrorEnabled = pwdErr != null
                tfPwdConfL.isErrorEnabled = pwdConfErr != null
            }
        })

        tfPwdConf.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                pwdConfErr = null
                if (s.isNullOrEmpty()) {
                    pwdConfErr = "Field is required"
                } else if (s.length < 6) {
                    pwdConfErr = "Must be at least 6 characters"
                } else if (s.length > 12) {
                    pwdConfErr = "Maximum 12 character"
                } else if (s.toString() != tfPwd.text.toString()) {
                    pwdConfErr = "Confirmation password not match"
                }
                tfPwdConfL.error = pwdConfErr
                tfPwdConfL.isErrorEnabled = pwdConfErr != null
            }
        })

        btnRegister.setOnClickListener {
            handleRegister()
        }

    }

    private fun handleRegister() {
//        validate input
        if (usernameErr != null || pwdErr != null || pwdConfErr != null) return
//        show dialog
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Register Success")
            setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled registration
                dialog.dismiss()
            }
        }.show()
//        remove all input
        tfUsername.text?.clear()
        tfPwd.text?.clear()
        tfPwdConf.text?.clear()
        tfUsernameL.isErrorEnabled = false
        tfPwdL.isErrorEnabled = false
        tfPwdConfL.isErrorEnabled = false
    }
}