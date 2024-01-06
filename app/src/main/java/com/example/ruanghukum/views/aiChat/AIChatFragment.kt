package com.example.ruanghukum.views.aiChat

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruanghukum.data.local.room.aiChat.AiChat
import com.example.ruanghukum.databinding.FragmentAIChatBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.paging.aiChat.ListAiChatAdapter
import com.example.ruanghukum.utils.NetworkResultState

class AIChatFragment : Fragment() {

    private var _binding: FragmentAIChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AIChatViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private var roomChat = ArrayList<AiChat>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAIChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.reverseLayout = true

        with(binding) {
            rvAiChat.layoutManager = layoutManager
            rvAiChat.scrollToPosition(0)

            viewModel.messages.observe(requireActivity()) { messages ->
                setList(messages)
            }

            btnBack.setOnClickListener{
                    activity?.onBackPressed()
            }

            btnChatSend.setOnClickListener{
                sendMessage(etChatMessage.text.toString())
                etChatMessage.text = null
            }

            etChatMessage.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && etChatMessage.text.isNotEmpty() && etChatMessage.text.isNotBlank() ) {
                    sendMessage(etChatMessage.text.toString())
                    etChatMessage.text = null
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    private fun sendMessage(message: String) {
        var messageModelSent = AiChat(
            message = message,
            flag = "SENT",
            createdAt = ""
            )
        viewModel.insertToLocal(messageModelSent)

        viewModel.sendMessage(messageModelSent).observe(requireActivity()) { resultState ->
            when (resultState) {
                is NetworkResultState.Success -> {
                    var messageModelReceived = AiChat(
                        message = resultState.data.data!!.payload!!.message!!,
                        flag = "RECEIVED",
                        createdAt = ""
                    )
                    viewModel.insertToLocal(messageModelReceived)
                }
                is NetworkResultState.Loading -> {
                }
                is NetworkResultState.Error -> {
                }
            }
        }
    }

    private fun setList(messages: List<AiChat>) {
        val adapter = ListAiChatAdapter(requireContext())
        adapter.updateList(messages)
        binding.rvAiChat.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}