package com.example.ruanghukum.views.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruanghukum.data.remote.response.PayloadItemDocumentHistory
import com.example.ruanghukum.databinding.FragmentNotificationBinding
import com.example.ruanghukum.factory.ViewModelFactory
import com.example.ruanghukum.paging.document.ListDocumentHistoryAdapter
import com.example.ruanghukum.utils.NetworkResultState

class NotificationFragment : Fragment() {

    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotificationViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        viewModel.getSession().observe(requireActivity()) { user ->
            updateDocHistories(user.token)
        }
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvDocument.layoutManager = layoutManager
    }

    private fun updateDocHistories(token: String) {
        viewModel.getDocumentHistory(token).observe(requireActivity()) { resultState ->
            when (resultState) {
                is NetworkResultState.Success -> {
                    if (resultState.data.data!!.payload!!.isEmpty()) {
                        with(binding) {
                            tvEmptyDocument.visibility = View.VISIBLE
                            btnCreateDocument.visibility = View.VISIBLE
                        }
                    } else {
                        with(binding) {
                            tvEmptyDocument.visibility = View.GONE
                            btnCreateDocument.visibility = View.GONE
                        }
                        setDocHistories(resultState.data.data.payload!!)
                    }
                }
                is NetworkResultState.Loading -> {
                }
                is NetworkResultState.Error -> {
                }
            }
        }
    }

    private fun setDocHistories(article: List<PayloadItemDocumentHistory?>) {

        val adapter = ListDocumentHistoryAdapter()
        adapter.submitList(article)
        binding.rvDocument.adapter = adapter

        adapter.setOnItemClickCallback(object : ListDocumentHistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(doc: PayloadItemDocumentHistory) {

                viewModel.navigateToDocumentPreview(
                    Navigation.findNavController(requireView()),
                    doc.path!!
                )
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_DOC_PATH = "extra_doc_path"
    }
}