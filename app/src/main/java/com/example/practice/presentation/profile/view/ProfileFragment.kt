package com.example.practice.presentation.profile.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentProfileBinding
import com.example.practice.model.Friend

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val friendsList = mutableListOf<Friend>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFakeProfile()
    }

    private fun initFakeProfile() {
        generateFakeFriends()
        with(binding) {
            photo.setImageResource(R.drawable.ic_profile)
            profileDetails.fullName.text = "Константинов Денис"
            profileDetails.date.text = "01 февраля 1980"
            profileDetails.professions.text = "Хирургия, Травматология"
            profileDetails.recycler.adapter = FriendsAdapter(friendsList)
        }
    }

    private fun generateFakeFriends() {
        repeat(10) {
            friendsList.add(Friend(R.drawable.ic_profile, "Lorem Lorem"))
        }
    }
}
