package com.example.featureregistrationimpl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.database.DataBaseRepository
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.R
import com.example.featureregistrationimpl.databinding.FragmentLoginBinding
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.LoginViewModel
import com.example.featureregistrationimpl.presentation.di.RegistrationComponentProvider
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private var userData: UserModel? = null

    @Inject
    lateinit var loginUserUseCase: LoginUserUseCase

    @Inject
    lateinit var router: LoginRouter

    lateinit var repository: DataBaseRepository

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.provideFactory(
            router,
            loginUserUseCase,
        )
    }

    override fun onAttach(context: Context) {
        val loginComponent = (requireActivity().application as RegistrationComponentProvider)
            .provideRegistrationComponent()
        loginComponent.injectLoginFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        repository = DataBaseRepository(requireContext())

        observeViewModel()

        binding?.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                viewModel.loginUser(requireContext(), email, password, repository)
            }
            tvRegister.setOnClickListener {
                viewModel.registerClick()
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            userLiveData.observe(viewLifecycleOwner) { user ->
                user?.let {
                    userData = user
                }
            }
        }
    }
}
