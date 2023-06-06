package com.example.feature_chat_impl.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.database.DataBaseRepository
import com.example.database.model.UserLocal
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_api.model.UserZodiacInfo
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageUseCase
import com.example.feature_chat_impl.presentation.domain.UserZodiacUseCase
import com.example.feature_chat_impl.presentation.di.MessageComponentProvider
import com.example.feature_chat_impl.presentation.model.Message
import com.example.feature_chat_impl.presentation.rv_chat.MessageAdapter
import com.example.feature_chat_impl.presentation.viewmodel.MessageViewModel
import com.example.feature_chat_impl.presentation.viewmodel.UserZodiacViewModel
import com.example.feature_sign_impl.R
import com.example.feature_sign_impl.databinding.FragmentChatBinding
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var chatRecyclerView: RecyclerView?=null
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private var binding: FragmentChatBinding? = null
    private var email: String? = ""
    private var otherEmail: String? = ""

    @Inject
    lateinit var router: ZodiacRouter

    @Inject
    lateinit var addMessageUseCase: AddMessageUseCase

    @Inject
    lateinit var messageUseCase: MessageUseCase

    @Inject
    lateinit var userZodiacUseCase: UserZodiacUseCase

    private val userViewModel: UserZodiacViewModel by viewModels {
        UserZodiacViewModel.provideFactory(
            router, userZodiacUseCase
        )
    }

    private val messageViewModel: MessageViewModel by viewModels {
        MessageViewModel.provideFactory(
            router, messageUseCase, addMessageUseCase
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var messageComponent =
            (requireActivity().application as MessageComponentProvider).provideMessageComponent()
        messageComponent.injectChatFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initObservers()
        val repository = DataBaseRepository(requireContext())
        var user: UserLocal? = null
        lifecycleScope.launch {
            user = repository.findUser()
            email = user?.email
            val zodiac = arguments?.getString("zodiac")
            getPartner(email!!, zodiac!!)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChatBinding.bind(view)
        chatRecyclerView = binding?.chatRecyclerView
        messageBox = binding?.messageBox!!
        sendButton = binding?.btn!!
        sendButton.setOnClickListener {
            addMessage(email, otherEmail, messageBox.text.toString())
            messageBox.setText("")
            updateRecyclerView(email!!, otherEmail!!)
        }
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.example.base.R.id.home_bottom -> {
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.profile_bottom -> {
                    router.openProfile()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.taro_bottom -> {
                    router.openAligment()
                    return@setOnItemSelectedListener true
                }
                com.example.base.R.id.zodiac_bottom -> {
                    router.openZodiac()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun initObservers() {
        userViewModel.user.observe(viewLifecycleOwner) {
            it.fold(onSuccess = {
                getUser(it)
            }, onFailure = { Log.e("Chat fragment", "this is user") })
        }

        messageViewModel.receiverMessages.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { listModel ->
                    chatRecyclerView?.run {
                        lifecycleScope.launch {
                            adapter = MessageAdapter(sortByTime(listModel.list))
                        }
                    }
                },
                onFailure = { Log.e("Chat fragment", "receiver") })
        }
    }

    private fun getPartner(zodiac: String, email: String) {
        userViewModel.getUser(zodiac, email)
    }

    private fun getUser(gotUser: UserZodiacInfo) {
        lifecycleScope.launch {
            try {
                gotUser.also {
                    binding?.usernameTextView?.text = it.username
                    otherEmail = it.email
                    rvCreator(email!!, otherEmail!!)
                    Log.e("otherEmail", otherEmail.toString())
                }
            } catch (error: Throwable) {
                Log.e("ChatFragment", error.toString())
            }
        }
    }

    private fun rvCreator(sender: String, receiver: String){
        messageViewModel.getByReceiver(sender, receiver)
    }

    private fun updateRecyclerView(sender: String, receiver: String) {
        messageViewModel.receiverMessages.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess = { listModel ->
                    Log.e("update", "i try to update")
                    chatRecyclerView?.run {
                        lifecycleScope.launch {
                            adapter = MessageAdapter(sortByTime(listModel.list))
                        }
                    }
                },
                onFailure = { Log.e("Chat fragment", "receiver") })
        }
        messageViewModel.getByReceiver(sender, receiver)
    }

    private fun addMessage(sender: String?, receiver: String?, text: String?) {
        lifecycleScope.launch {
            messageViewModel.addMessage(sender, receiver, text)
        }
    }

    private fun sortByTime(list: List<MessageInfo>): List<Message>{
        val chats = mutableListOf<Message>()
        for (message in list) {
            if (message.sender == email) {
                chats.add(Message(message.text, message.sender!!, message.receiver!!, message.date!!, true))
            }else{
                chats.add(Message(message.text, message.sender!!, message.receiver!!, message.date!!, false))
            }
        }
        chats.sortBy { it.date }
        return chats
    }
}