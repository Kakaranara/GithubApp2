package com.example.wahyugithub2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wahyugithub2.ui.adapter.FollowListAdapter
import com.example.wahyugithub2.databinding.FragmentFollowerBinding
import com.example.wahyugithub2.databinding.FragmentFollowingBinding
import com.example.wahyugithub2.datacenter.viewmodel.FollowViewModel

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("username")

        val viewModel = ViewModelProvider(requireActivity()).get(FollowViewModel::class.java)

        viewModel.getFollowing(args!!)
        viewModel.dataFollowing.observe(requireActivity()){
            binding.rvFollowing.adapter = FollowListAdapter(it)
        }

        viewModel.isFollowingLoading.observe(requireActivity()){
            showLoading(it)
        }

        val manager = LinearLayoutManager(requireActivity())
        binding.rvFollowing.layoutManager = manager
    }

    fun showLoading(isLoading : Boolean){
        binding.progressBar3.visibility = when(isLoading){
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}