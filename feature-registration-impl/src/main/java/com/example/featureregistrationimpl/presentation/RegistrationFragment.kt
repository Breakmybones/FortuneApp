package com.example.featureregistrationimpl.presentation

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.featureregistrationimpl.R
import com.example.featureregistrationimpl.databinding.FragmentRegistrationBinding
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.domain.RegisterUserUseCase
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null

    @Inject
    lateinit var loginUserUseCase: LoginUserUseCase

    @Inject
    lateinit var registerUserUseCase: RegisterUserUseCase

    private val viewModel: RegistrationViewModel by viewModels {
        RegistrationViewModel.provideFactory(
            registerUserUseCase,
            loginUserUseCase
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrationBinding.bind(view)

        binding?.run {
            btnRegister.setOnClickListener {
                registerUser()
                navigateToHomeFragment()
            }
        }
    }

    private fun registerUser() {
        binding?.apply {
            val userName = etName.toString()
            val userEmail = etEmail.toString()
            val userPassword = etPassword.toString()
            val userBirth = etBirth.toString()
            if (userName.isEmpty()) {
                etName.error = "Заполните имя"
                etName.requestFocus()
            }
            if (userPassword.isEmpty()) {
                etName.error = "Заполните пароль"
            }
            if (userEmail.isEmpty()) {
                etName.error = "Заполните почту"
            }
            if (userBirth.isEmpty()) {
                etName.error = "Заполните имя"
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                etEmail.error = "Неверная почта"
                etName.requestFocus()
            }



        }
    }

    private fun navigateToHomeFragment() {
        TODO("Not yet implemented")
    }

}

