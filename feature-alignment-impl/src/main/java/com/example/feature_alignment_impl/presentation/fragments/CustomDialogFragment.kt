package com.example.feature_alignment_impl.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.FragmentDialogBinding
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_alignment_impl.presentation.routers.AlignmentsRouter
import javax.inject.Inject

class CustomDialogFragment() : DialogFragment(R.layout.fragment_dialog) {

    @Inject
    lateinit var router: AlignmentsRouter

    private var binding: FragmentDialogBinding? = null

    private lateinit var sharedPreferences: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var alignmentComponent = (requireActivity().application as AlignmentComponentProvider)
            .provideAlignmentComponent()
        alignmentComponent.injectDialogFragment(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString("ALIGNMENT")
        var bundle : Bundle
        title.also {
            bundle = Bundle().apply {
                putString("ALIGNMENT", title)
            }
        }
        val alignment = sharedPreferences.getString("$title", "")
        if(alignment != title){
            super.onViewCreated(view, savedInstanceState)
            val description = arguments?.getString("description")
            binding = FragmentDialogBinding.bind(view)
            binding?.also{
                it.tvTitle.text = title
                it.tvDescription.text = description
                it.btn.setOnClickListener{
                    sharedPreferences.edit().putString("$title", "$title").apply()
                    dismiss()
                    router.openSelectCards(bundle)
                }
            }
        }else{
            router.openSelectCards(bundle)
        }

    }

}
