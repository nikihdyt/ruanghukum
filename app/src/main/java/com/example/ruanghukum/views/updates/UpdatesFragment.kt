package com.example.ruanghukum.views.updates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ruanghukum.R

class UpdatesFragment : Fragment() {

    companion object {
        fun newInstance() = UpdatesFragment()
    }

    private lateinit var viewModel: UpdatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_updates, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdatesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}