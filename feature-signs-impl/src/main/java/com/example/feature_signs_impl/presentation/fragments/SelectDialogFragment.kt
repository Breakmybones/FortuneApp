package com.example.feature_signs_impl.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.database.DataBaseRepository
import com.example.database.model.UserLocal
import com.example.feature_signs_impl.R
import com.example.feature_signs_impl.databinding.FragmentSelectDialogBinding
import com.example.feature_signs_impl.presentation.di.ZodiacComponentProvider
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectDialogFragment : DialogFragment(R.layout.fragment_select_dialog) {
    @Inject
    lateinit var router: ZodiacRouter

    private var binding: FragmentSelectDialogBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var zodiacComponent = (requireActivity().application as ZodiacComponentProvider)
            .provideZodiacComponent()
        zodiacComponent.injectSelectDialogFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = DataBaseRepository(requireContext())
        var user: UserLocal? = null
        var title: String? = null
        lifecycleScope.launch {
            user = repository.findUser()
            title = user?.sign
        }
        title = when(title){
            "Овен"-> "oven"
            "Телец"-> "telec"
            "Близнецы"-> "bliznec"
            "Рак"-> "rac"
            "Лев"-> "lev"
            "Дева"-> "deva"
            "Весы"-> "vesy"
            "Скорпион"-> "scorpion"
            "Стрелец"-> "strelec"
            "Козерог"-> "kozerog"
            "Водолей"-> "vodoley"
            else-> "ryby"
        }
        var bundle : Bundle
        title.also {
            bundle = Bundle().apply {
                putString("sign", title)
            }
        }
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectDialogBinding.bind(view)
        binding?.btnFriend?.setOnClickListener {
            router.openFriendshipFragment(bundle)
        }
        binding?.btnLove?.setOnClickListener {
            router.openLoveFragment(bundle)
        }


    }

    //    private fun getCurrentUser(): UserLocal? {
//        val repository = DataBaseRepository(requireContext())
//        var user: UserLocal? = null
//        return try {
//            lifecycleScope.launch {
//                user = repository.findUser()
//            }
//            user
//        } catch (e: Exception) {
//            null;
//        }
//    }

}