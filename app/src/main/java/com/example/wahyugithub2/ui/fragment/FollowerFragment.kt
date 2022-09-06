package com.example.wahyugithub2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.datacenter.viewmodel.FollowViewModel
import com.example.wahyugithub2.ui.adapter.FollowListAdapter
import com.example.wahyugithub2.databinding.FragmentFollowerBinding
import com.example.wahyugithub2.showLoading

class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("username")

        val viewModel = ViewModelProvider(requireActivity())[FollowViewModel::class.java]

        viewModel.getFollower(args!!)
        viewModel.dataFollower.observe(requireActivity()){
            binding.rvFollower.adapter = FollowListAdapter(it)
        }

        viewModel.isFollowerLoading.observe(requireActivity()){
            binding.progressBar2.showLoading(it)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}