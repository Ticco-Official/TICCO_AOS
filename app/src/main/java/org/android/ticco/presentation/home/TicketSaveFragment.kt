package org.android.ticco.presentation.home

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.R
import org.android.ticco.databinding.FragmentTicketSaveBinding
import org.android.ticco.presentation.base.BaseFragment
import org.android.ticco.presentation.util.BindingAdapters.setTicketImg
import org.android.ticco.presentation.util.SnackBarUtil
import java.io.FileOutputStream

@AndroidEntryPoint
class TicketSaveFragment : BaseFragment<FragmentTicketSaveBinding>(R.layout.fragment_ticket_save) {

    private val arg: TicketSaveFragmentArgs by navArgs()

    override fun initView() {
        setImageView()
        setListeners()
    }

    private fun setImageView() {
        Glide.with(requireContext())
            .load(arg.imageUrl)
            .placeholder(R.drawable.img_welcome)
            .error(R.drawable.img_welcome)
            .into(binding.ivTicket)
    }

    private fun setListeners() {
        binding.btnTicketSave.setOnClickListener {
            setImageToBitmap()
        }
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun saveFile(fileName: String, bitmap: Bitmap): Uri? {
        val contentValue = ContentValues()
        contentValue.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        contentValue.put(MediaStore.Images.Media.MIME_TYPE, "image/png")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValue.put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = requireActivity().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValue
        )

        if (uri != null) {
            val scriptor = requireActivity().contentResolver.openFileDescriptor(uri, "w")

            if (scriptor != null) {
                val fos = FileOutputStream(scriptor.fileDescriptor)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentValue.clear()
                    contentValue.put(MediaStore.Images.Media.IS_PENDING, 0)
                    requireActivity().contentResolver.update(uri, contentValue, null, null)
                }
            }
        }

        return uri
    }

    private fun setImageToBitmap() {
        val fileName = System.currentTimeMillis().toString() + ".png"
        if (binding.ivTicket.drawable is BitmapDrawable) {
            val bitmap = (binding.ivTicket.drawable as BitmapDrawable).bitmap
            saveFile(fileName, bitmap)
        } else {
            val drawable = binding.ivTicket.drawable
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            saveFile(fileName, bitmap)
        }
        findNavController().popBackStack()
        setImageSaveMessage()
    }

    private fun setImageSaveMessage() {
        SnackBarUtil.make(binding.clTicket as View).show()
    }


}