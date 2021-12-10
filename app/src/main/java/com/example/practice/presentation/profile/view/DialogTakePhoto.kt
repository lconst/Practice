package com.example.practice.presentation.profile.view

import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.practice.databinding.DialogTakePhotoBinding
import java.io.File

class DialogTakePhoto : DialogFragment() {

    private val viewModel: ProfileViewModel by viewModels({ requireParentFragment() })
    private val tempDirectory by lazy { requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES) }
    private lateinit var uri: Uri

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val dialogBinding = DialogTakePhotoBinding.inflate(LayoutInflater.from(it))
            builder.setView(dialogBinding.root)
            dialogBinding.makePhoto.setOnClickListener { handleMakePhoto() }
            dialogBinding.deletePhoto.setOnClickListener { handleDeletePhoto() }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
    }

    private fun handleMakePhoto() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            tempDirectory
        )
        uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        getPhoto.launch(uri)
    }

    private fun handleDeletePhoto() {
        clearDirectory()
        viewModel.photoUri.value = null
        dismiss()
    }

    private fun clearDirectory() {
        val files = tempDirectory?.listFiles()
        files?.forEach { it.delete() }
    }

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                viewModel.photoUri.value = uri
            }
            dismiss()
        }

    companion object {
        const val TAG = "DialogTakePhoto"
    }
}
