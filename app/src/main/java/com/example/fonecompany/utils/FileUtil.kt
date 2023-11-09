package com.example.fonecompany.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.InputStream

object FileUtil {
    fun saveFile(context: Context, inputStream: InputStream, fileName: String): Uri? {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val filePath = File(path, "Fone Company").also { it.mkdir() }
        val file = File(filePath, fileName)
        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        inputStream.close()
        return FileProvider.getUriForFile(context, "com.example.fonecompany.provider", file)
    }

}