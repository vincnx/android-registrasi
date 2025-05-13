package com.vincnx.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _fnameErr = MutableLiveData("field is required")
    val fnameErr: LiveData<String> = _fnameErr

    private val _fname = MutableLiveData("")
    val fname: LiveData<String> = _fname

    private val _usernameErr = MutableLiveData("field is required")
    val usernameErr: LiveData<String> = _usernameErr

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _pwdErr = MutableLiveData("field is required")
    val pwdErr: LiveData<String> = _pwdErr

    private val _pwd = MutableLiveData("")
    val pwd: LiveData<String> = _pwd

    private val _pwdConf = MutableLiveData("")
    val pwdConf: LiveData<String> = _pwdConf

    private val _pwdConfErr = MutableLiveData("field is required")
    val pwdConfErr: LiveData<String> = _pwdConfErr


    fun setFname(fname: String) {
        _fname.value = fname
    }

    fun validateFname(fname: String) {
        _fnameErr.value = null
        if (fname.isEmpty()) {
            _fnameErr.value = "field is required"
        }
    }

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

        if (pwdConf.value.toString().isNotEmpty()) validatePwdConf(pwdConf.value.toString())
    }

    fun setPwdConf(pwdConf: String) {
        _pwdConf.value = pwdConf
    }

    fun validatePwdConf(pwdConf: String) {
        _pwdConfErr.value = null
        if (pwdConf.isEmpty()) {
            _pwdConfErr.value = "field is required"
        } else if (pwdConf.length < 6) {
            _pwdConfErr.value = "must be at least 6 characters"
        } else if (pwdConf.length > 12) {
            _pwdConfErr.value = "maximum 12 characters"
        } else if (pwdConf != pwd.value) {
            _pwdConfErr.value = "confirmation password not match"
        }
    }
}