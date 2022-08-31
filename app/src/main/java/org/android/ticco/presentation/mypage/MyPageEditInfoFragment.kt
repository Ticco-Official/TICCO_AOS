package org.android.ticco.presentation.mypage

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentMyPageEditInfoBinding
import org.android.ticco.presentation.base.BaseFragment
import org.android.ticco.presentation.util.EventObserver
import java.io.File

@AndroidEntryPoint
class MyPageEditInfoFragment :
    BaseFragment<FragmentMyPageEditInfoBinding>(R.layout.fragment_my_page_edit_info) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun initView() {
        binding.vm = myPageViewModel
        setUserInfo()
        setListeners()
        setTextWatcher()
        initIsSuccessObserver()
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.ivProfile.setOnClickListener { setAlbumView() }
        binding.ivCamera.setOnClickListener { setAlbumView() }
    }

    private fun setUserInfo() {
        myPageViewModel.setUserInfo()
    }

    private fun setTextWatcher() {
        binding.etNickname.addTextChangedListener {
            myPageViewModel.nickname.value = binding.etNickname.text.toString()
        }
    }

    private fun initIsSuccessObserver() {
        myPageViewModel.isSuccess.observe(viewLifecycleOwner, EventObserver {
            if (it) findNavController().popBackStack()
        })
    }

    private val fromAlbumActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        result.data?.let {
            if (it.data != null) {
                myPageViewModel.setProfileImgUri(
                    it.data as Uri,
                    File(absolutelyPath(it.data, requireContext()))
                )
            }
        }
    }

    private fun setAlbumView() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                fromAlbumActivityLauncher.launch(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                )
            }
            else -> {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_STORAGE_PERMISSION
                )
            }
        }
    }

    private fun absolutelyPath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        val result = c?.getString(index!!)

        return result!!
    }

    companion object {
        const val REQUEST_READ_STORAGE_PERMISSION = 1
    }


}