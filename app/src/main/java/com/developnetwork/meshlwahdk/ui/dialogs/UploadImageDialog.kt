package com.developnetwork.meshlwahdk.ui.dialogs

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.developnetwork.meshlwahdk.R
import com.developnetwork.meshlwahdk.utils.ImageCompressor
import com.ivestment.doctorna.utils.PathUtil
import kotlinx.android.synthetic.main.dialog_upload_image.*
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class UploadImageDialog(
    private val titleString: String?,
    private val returnPath: ((path: String) -> Unit)?
) :
    DialogFragment() {
    constructor() : this(null, null)

    override fun onStart() {
        super.onStart()
        val windowWidth = getWindowWidth()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            (windowWidth * 0.9).toInt()
        )
    }


    private fun getWindowWidth(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity?.display
            display?.getRealMetrics(displayMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity?.windowManager?.defaultDisplay
            @Suppress("DEPRECATION")
            display?.getMetrics(displayMetrics)
        }
        return displayMetrics.widthPixels
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_upload_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = titleString

        camBTN.setOnClickListener {
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.CAMERA))

        }

        galleryBTN.setOnClickListener {
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (it.contains(Manifest.permission.CAMERA) && it[Manifest.permission.CAMERA] == true)
                dispatchTakePictureIntent()
            else if (it.contains(Manifest.permission.READ_EXTERNAL_STORAGE) && it[Manifest.permission.READ_EXTERNAL_STORAGE] == true)
                openPhotoGallery()
        }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                data?.let { data ->
                    var selectedImage: Uri? = data.data
                    if (selectedImage == null) {
                        //File object of camera image
                        val file = File(
                            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            System.currentTimeMillis().toString() + ".jpg"
                        )
                        saveLabel(data.extras?.get("data") as Bitmap, file)

                        //Uri of camera image
                        val uri = Uri.fromFile(file)
                        selectedImage = uri

                    }

                    try {

                        selectedImage?.let {
                            val originalFile = File(PathUtil.getPath(requireContext(), it))

                            ImageCompressor.compressBitmap(
                                requireContext(),
                                originalFile
                            ) { file ->
                                returnPath?.invoke(file.path)
                                dismiss()
                            }

                        }
                    } catch (e: Exception) {
                        Timber.tag("image_path").e(e)
                    }
                }
            }
        }

    private fun openPhotoGallery() {
        val galleryIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.setType("image/*")

        resultLauncher.launch(galleryIntent)
    }


    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(takePictureIntent)
    }

    fun saveLabel(bitmap: Bitmap, file: File) {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos)
        val bitmapdata: ByteArray = bos.toByteArray()
        val fos: FileOutputStream
        try {
            fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
        } catch (e: Throwable) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}