package com.example.featureregistrationimpl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.R
import com.example.featureregistrationimpl.databinding.FragmentRegistrationBinding
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.domain.RegisterUserUseCase
import com.example.featureregistrationimpl.presentation.di.RegisterRouter
import com.example.featureregistrationimpl.presentation.RegistrationViewModel
import com.example.featureregistrationimpl.presentation.di.RegistrationComponentProvider
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null
    private var userData: UserModel? = null


    @Inject
    lateinit var loginUserUseCase: LoginUserUseCase

    @Inject
    lateinit var registerUserUseCase: RegisterUserUseCase

    @Inject
    lateinit var router: RegisterRouter

    private val viewModel: RegistrationViewModel by viewModels {
        RegistrationViewModel.provideFactory(
            router,
            registerUserUseCase,
            loginUserUseCase
        )
    }

    override fun onAttach(context: Context) {
        val loginComponent = (requireActivity().application as RegistrationComponentProvider)
            .provideRegistrationComponent()
        loginComponent.injectRegisterFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrationBinding.bind(view)

        binding?.run {
            btnRegister.setOnClickListener {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        binding?.apply {
            val male = arrayOf("Женщина", "Мужчина")
            var flag = true
            val userName = etName.text.toString()
            val userEmail = etEmail.text.toString()
            val userPassword = etPassword.text.toString()
            val userBirth = etBirth.text.toString()
            val userPassword2 = etPassword2.text.toString()

            val userMale: Boolean =  etMale.selectedItem.toString() != "Женщина"
            if (userName.isNullOrEmpty()) {
                etName.error = "Заполните имя"
                etName.requestFocus()
                flag = false
            }
            if (userPassword.isEmpty() || userPassword.length < 6) {
                etPassword.error = "Заполните пароль"
                flag = false
            }
            if (!checkEmail(email = userEmail)) {
                etEmail.error = "Введите правильный адрес"
                flag = false
            }
            if (userBirth.isEmpty()) {
                etBirth.error = "Заполните дату рождения"
                etBirth.requestFocus()
                flag = false
            }
            if (userPassword != userPassword2) {
                Toast.makeText(
                    requireContext(),
                    "Пароли не сопадают",
                    Toast.LENGTH_SHORT).show()
                flag = false
            }
            if (flag) {
                viewModel.registerUser(userName, userEmail, userPassword, userBirth, userMale)
                Toast.makeText(requireContext(), "Регистрация прошла", Toast.LENGTH_SHORT).show()
                router.openHome()
            }
        }
    }

    private fun checkEmail(email: String): Boolean {
        var flag = true
        if (email.isEmpty()) {
            flag = false
        }
        if (!email.contains("@")) {
            flag = false
        }
        return flag
    }

    private fun observeViewModel() {
        with(viewModel) {
            user.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                userData = it
            }
        }
    }
}

