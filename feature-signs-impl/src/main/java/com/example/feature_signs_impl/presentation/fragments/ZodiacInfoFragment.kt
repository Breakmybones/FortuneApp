package com.example.feature_signs_impl.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.database.DataBaseRepository
import com.example.database.model.UserLocal
import com.example.feature_signs_impl.R
import com.example.feature_signs_impl.databinding.FragmentZodiacInfoBinding
import com.example.feature_signs_impl.presentation.di.ZodiacComponentProvider
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class ZodiacInfoFragment: Fragment(R.layout.fragment_zodiac_info) {

    private var binding: FragmentZodiacInfoBinding? = null

    @Inject
    lateinit var router: ZodiacRouter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var zodiacComponent = (requireActivity().application as ZodiacComponentProvider)
            .provideZodiacComponent()
        zodiacComponent.injectZodiacInfoFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentZodiacInfoBinding.bind(view)
        val name = arguments?.getString("sign")
        binding?.run{
            tvName.text = name
            val imageName = name?.lowercase(Locale.ROOT)
            val resourceId = resources.getIdentifier(imageName, "drawable", context?.packageName)
            ivCard.setImageResource(resourceId)
            btn.setOnClickListener{
                val email = getCurrentUser()?.email
                var bundle : Bundle
                email.also {
                    bundle = Bundle().apply {
                        putString("name", email)
                        putString("zodiac", name)
                    }
                }
                router.openChat(bundle)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
    private fun getCurrentUser(): UserLocal? {
        val repository = DataBaseRepository(requireContext())
        var user: UserLocal? = null
        return try {
            lifecycleScope.launch {
                user = repository.findUser()
            }
            user
        } catch (e: Exception) {
            null;
        }
    }
}