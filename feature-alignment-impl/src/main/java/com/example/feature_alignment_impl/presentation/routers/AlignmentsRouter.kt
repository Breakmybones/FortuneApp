package com.example.feature_alignment_impl.presentation.routers

import android.content.Context
import android.os.Bundle

interface AlignmentsRouter {

    fun openHome(context: Context)

    fun openStart(context: Context)

    fun openSelectCards(bundle: Bundle)

    fun openDialogFragment(bundle: Bundle)

    fun openProfile()

    fun openAligment()

    fun openZodiac()

}