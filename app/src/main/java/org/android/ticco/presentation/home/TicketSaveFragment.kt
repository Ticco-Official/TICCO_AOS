package org.android.ticco.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ticket_etc.*
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketSaveBinding
import org.android.ticco.presentation.base.BaseFragment
import org.android.ticco.presentation.util.SnackBarUtil
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class TicketSaveFragment : BaseFragment<FragmentTicketSaveBinding>(R.layout.fragment_ticket_save) {

    override fun initView() {
        setListeners()
    }

    private fun setListeners() {
        binding.btnTicketSave.setOnClickListener {
            imgSaveOnClick()
        }
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
    }

    fun saveFile(fileName: String, mimeType: String, bitmap: Bitmap): Uri?{
        var CV = ContentValues()
        CV.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        CV.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CV.put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, CV)

        if (uri != null) {
            var scriptor = requireActivity().contentResolver.openFileDescriptor(uri, "w")

            if (scriptor != null) {
                val fos = FileOutputStream(scriptor.fileDescriptor)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    CV.clear()
                    CV.put(MediaStore.Images.Media.IS_PENDING, 0)
                    requireActivity().contentResolver.update(uri, CV, null, null)
                }
            }
        }

        return uri
    }


    fun imageExternalSave(context: Context, bitmap: Bitmap, path: String): Boolean {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {

            val rootPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString()
            val dirName = "/" + path
            val fileName = System.currentTimeMillis().toString() + ".png"
            val savePath = File(rootPath + dirName)
            savePath.mkdirs()

            val file = File(savePath, fileName)
            if (file.exists()) file.delete()

            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()

                //갤러리 갱신
                context.sendBroadcast(
                    Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + Environment.getExternalStorageDirectory())
                    )
                )

                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun imgSaveOnClick() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //Q 버전 이상일 경우. (안드로이드 10, API 29 이상일 경우)
            val fileName = System.currentTimeMillis().toString() + ".png"
            if(binding.ivTicket.drawable is BitmapDrawable) {
                val bitmap = (binding.ivTicket.drawable as BitmapDrawable).bitmap
                saveFile(fileName,"image/png",bitmap)
            } else {
                val drawable = binding.ivTicket.drawable
                val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                saveFile(fileName,"image/png",bitmap)
            }
            findNavController().popBackStack()
            setImageSaveMessage()
        }else {
            // Q 버전 이하일 경우. 저장소 권한을 얻어온다.
            val writePermission = ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if(writePermission == PackageManager.PERMISSION_GRANTED) {
                val fileName = System.currentTimeMillis().toString() + ".png"
                if(binding.ivTicket.drawable is BitmapDrawable) {
                    val bitmap = (binding.ivTicket.drawable as BitmapDrawable).bitmap
                    saveFile(fileName,"image/png",bitmap)
                } else {
                    val drawable = binding.ivTicket.drawable
                    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                    saveFile(fileName,"image/png",bitmap)
                }
                findNavController().popBackStack()
                setImageSaveMessage()
            } else {
                val requestExternalStorageCode = 1

                val permissionStorage = arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                ActivityCompat.requestPermissions(requireActivity(), permissionStorage, requestExternalStorageCode)
            }
        }

    }

    private fun setImageSaveMessage() {
        SnackBarUtil.make(binding.clTicket as View).show()
    }


}