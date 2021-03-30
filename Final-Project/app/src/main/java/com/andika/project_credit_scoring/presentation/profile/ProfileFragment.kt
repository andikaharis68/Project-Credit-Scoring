package com.andika.project_credit_scoring.presentation.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentProfileBinding
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.model.user.RequestPassword
import com.andika.project_credit_scoring.model.user.RequestUser
import com.andika.project_credit_scoring.presentation.login.LoginViewModel
import com.bumptech.glide.Glide
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_edit_password.view.*
import kotlinx.android.synthetic.main.dialog_edit_profile.*
import kotlinx.android.synthetic.main.dialog_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel
    private var selectedImage: Uri? = null
    lateinit var requestUser: RequestUser
    lateinit var requestPassword: RequestPassword
    var uri = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {

            viewModel.getUser().observe(requireActivity()) {
                Glide.with(requireActivity()).load(it?.data?.profilePicture).into(profile_picture)
                profileTextName.text = it?.data?.fullName
                profileTextUsername.text = it?.data?.username
                profileTextEmail.text = it?.data?.email
                profileTextRole.text = it?.data?.role
            }

            profileBtnBack.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }

            profileEditImage.setOnClickListener {
                loadImagefromGallery(it)
            }

            profileEditBtn.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.dialog_edit_profile, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
                dialogView.apply {
                    viewModel.getUser().observe(requireActivity()) {
                        dialog_edit_text_name.setText(it?.data?.fullName,TextView.BufferType.EDITABLE)
                        dialog_edit_text_username.setText(it?.data?.username,TextView.BufferType.EDITABLE)
                        dialog_edit_text_email.setText(it?.data?.email,TextView.BufferType.EDITABLE)
                    }
                    dialog_edit_btn_cancel.setOnClickListener {
                        alertDialog.hide()
                    }
                    dialog_edit_btn_save.setOnClickListener {
                        requestUser = RequestUser(
                            username = dialog_edit_text_username.text.toString(),
                            fullName =  dialog_edit_text_name.text.toString(),
                            email = dialog_edit_text_email.text.toString(),
                            profilePicture = uri
                        )
                        editUser(requestUser)
                        alertDialog.hide()
                    }
                }
            }

            profileChangePassword.setOnClickListener {
                val dialogViewPassword = LayoutInflater.from(requireContext())
                    .inflate(R.layout.dialog_edit_password, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogViewPassword)
                val alertDialog = dialogBuilder.show()
                dialogViewPassword.apply {
                    dialog_password_btn_cancel.setOnClickListener {
                        alertDialog.hide()
                    }
                    dialog_password_btn_save.setOnClickListener {
                        requestPassword = RequestPassword(
                            oldPassword = dialog_password_text_old.text.toString(),
                            password = dialog_password_text_new.text.toString()
                        )
                        editPassword(requestPassword)
                        alertDialog.hide()
                    }
                }
            }
            return binding.root
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }


    fun loadImagefromGallery(view: View?) {
        // buat intentnya
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Start Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            // saat gambar diambil
            if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK && null != data) {
                // dapatkan gambar dari data intent
                selectedImage = data.data!!
                uploadToCloudinary(getPath(selectedImage)!!)
            } else {
                Toast.makeText(
                    requireContext(), "Anda belum mengambil gambar",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Kesalahan terjadi", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = activity?.contentResolver?.query(uri!!, projection, null, null, null) ?: return null
        val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val s: String = cursor.getString(columnIndex)
        cursor.close()
        return s
    }

    private fun uploadToCloudinary(filepath: String) {
        MediaManager.get().upload(filepath).unsigned("ve2u0qv8").callback(object : UploadCallback {
            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                uri = resultData?.get("url").toString()
                Glide.with(requireActivity()).load(uri).into(profile_picture)
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                Toast.makeText(requireContext(), "on progress", Toast.LENGTH_SHORT).show()
            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                Toast.makeText(requireContext(), "pending", Toast.LENGTH_SHORT).show()
            }

            override fun onError(requestId: String?, error: ErrorInfo?) {
                error
                Toast.makeText(
                    requireContext(),
                    "Task Not successful" + error,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onStart(requestId: String?) {

                Toast.makeText(requireContext(), "Start", Toast.LENGTH_SHORT).show()
            }
        }).dispatch()
    }

    fun editUser(requestUser: RequestUser) =
        viewModel.editUser(requestUser).observe(requireActivity()){
            when (it?.code) {
                200 -> {
                    Toast.makeText(
                        requireContext(),
                        "success edit user",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                }
                100 -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    fun editPassword(requestPassword: RequestPassword) =
        viewModel.editPassword(requestPassword).observe(requireActivity()){
            when (it?.code) {
                200 -> {
                    Toast.makeText(
                        requireContext(),
                        "success edit password",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
                }
                100 -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    companion object {
        private const val RESULT_LOAD_IMG = 1
    }
}