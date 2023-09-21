package com.imaginato.homeworkmvvm.ui.login

import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.databinding.ActivityMainBinding
import com.imaginato.homeworkmvvm.exts.hideKeyboard
import com.imaginato.homeworkmvvm.exts.isConnected
import com.imaginato.homeworkmvvm.exts.showSnackBar
import com.imaginato.homeworkmvvm.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        handleListener()
        initObserve()
    }

    private fun handleListener() {
        binding.btnLogin.setOnClickListener {
            hideKeyboard()
            viewModel.checkLoginValidation(
                binding.etUserName.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }
    }

    private fun initObserve() {
        viewModel.loginResultLiveData.observe(this) {
            binding.root.showSnackBar(it)
        }
        viewModel.progress.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.loginValidationEvent.observe(this) { validation ->
            when (validation) {
                LoginValidation.IS_USERNAME_BLANK -> {
                    binding.root.showSnackBar(getString(R.string.text_please_enter_username))
                }

                LoginValidation.IS_PASSWORD_BLANK -> {
                    binding.root.showSnackBar(getString(R.string.text_please_enter_password))
                }

                LoginValidation.VALID_INPUT -> {
                    if (this.isConnected()) {
                        viewModel.doLogin(
                            binding.etUserName.text.toString().trim(),
                            binding.etPassword.text.toString().trim()
                        )
                    } else {
                        binding.root.showSnackBar(getString(R.string.text_err_check_internet))
                    }
                }
            }
        }
    }
}