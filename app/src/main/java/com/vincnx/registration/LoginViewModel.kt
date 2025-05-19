package com.vincnx.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _usernameErr = MutableLiveData("field is required")
    val usernameErr: LiveData<String> = _usernameErr

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _pwdErr = MutableLiveData("field is required")
    val pwdErr: LiveData<String> = _pwdErr

    private val _pwd = MutableLiveData("")
    val pwd: LiveData<String> = _pwd

    fun setUsername(username: String) {
        _username.value = username
    }

    fun validateUsername(username: String) {
        _usernameErr.value = null
        if (username.isEmpty()) {
            _usernameErr.value = "field is required"
        } else if (username.length < 5) {
            _usernameErr.value = "must be at least 5 characters"
        }
    }

    fun setPwd(pwd: String) {
        _pwd.value = pwd
    }

    fun validatePwd(pwd: String) {
        _pwdErr.value = null
        if (pwd.isEmpty()) {
            _pwdErr.value = "field is required"
        } else if (pwd.length < 6) {
            _pwdErr.value = "must be at least 6 characters"
        } else if (pwd.length > 12) {
            _pwdErr.value = "maximum 12 characters"
        }
    }
}