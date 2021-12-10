package com.example.practice.presentation.profile.view

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val photoUri = MutableLiveData<Uri>()
}
