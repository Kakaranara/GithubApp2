package com.example.wahyugithub2.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wahyugithub2.ui.fragment.FollowerFragment
import com.example.wahyugithub2.ui.fragment.FollowingFragment

class ViewPagerAdapter(activity : AppCompatActivity,private val bundle: Bundle) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val follower = FollowerFragment()
        follower.arguments = bundle

        val following = FollowingFragment()
        following.arguments = bundle

        when(position){
            0 -> return follower
            1 -> return following
        }
        return Fragment()
    }
}