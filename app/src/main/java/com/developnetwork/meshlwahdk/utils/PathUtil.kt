package com.ivestment.doctorna.utils
import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore

object PathUtil {
    @SuppressLint("NewApi")
    fun getPath(context: Context, uri: Uri): String? {
        var newUri: Uri = uri
        val needToCheckUri = Build.VERSION.SDK_INT >= 19
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(
                context.applicationContext,
                newUri
            )
        ) {
            if (isExternalStorageDocument(newUri)) {
                val docId = DocumentsContract.getDocumentId(newUri)
                val split = docId.split(":".toRegex()).toTypedArray()
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
            } else if (isDownloadsDocument(newUri)) {
                val id = DocumentsContract.getDocumentId(newUri)
                newUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
            } else if (isMediaDocument(newUri)) {
                val docId = DocumentsContract.getDocumentId(newUri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("image" == type) {
                    newUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    newUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    newUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                selection = "_id=?"
                selectionArgs = arrayOf(split[1])
            }
        }
        if ("content".equals(newUri.scheme, ignoreCase = true)) {
            val projection =
                arrayOf(MediaStore.Images.Media.DATA)
            try {
                val cursor = context.contentResolver
                    .query(newUri, projection, selection, selectionArgs, null)
                cursor?.let {
                    val column_index: Int =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index)
                    }
                }

            } catch (e: Exception) {
            }
        } else if ("file".equals(newUri.scheme, ignoreCase = true)) {
            return newUri.path
        }
        return uri.path
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.getAuthority()
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.getAuthority()
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.getAuthority()
    }
}