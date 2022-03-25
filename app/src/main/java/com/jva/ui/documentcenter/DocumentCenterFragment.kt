package com.jva.ui.documentcenter

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jva.databinding.ActivityDocumentsBinding
import com.jva.ui.adapter.DocumentAdapter
import com.jva.ui.viewmodels.JVMViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class DocumentCenterFragment : Fragment() {
    private val viewModel: JVMViewModel by viewModels()
    private var _binding: ActivityDocumentsBinding? = null
    private var msg: String? = ""
    private var lastMsg = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = ActivityDocumentsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        getData()

        downloadData()

        return root
    }

    private fun downloadData() {

        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) !==
            PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
        }
        else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }




        viewModel.responseDocumentCenter.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            it?.let {

                downloadImage(it)
            }

        })
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
    private fun getData() {
        showProgress(true)
        viewModel.getDocumentCenterResponse()

        viewModel.responseDocumentCenterResponse.observe(viewLifecycleOwner, Observer {
            showProgress(false)
            it?.let {

                binding.rvDocuments.adapter = DocumentAdapter(requireContext(),viewModel,it.data!!.data) {
//                    val intent = Intent(requireActivity(), WebActivity::class.java)
//                    intent.putExtra("news",it.link)
//                    requireActivity().startActivity(intent)
                }
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showProgress(show: Boolean) {
        _binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }



    @SuppressLint("Range")
    val directory = File(Environment.DIRECTORY_PICTURES)
    private fun downloadImage(url: String) {

        if (!directory.exists()) {
            directory.mkdirs()
        }
//        openFile( "$directory" + File.separator + url.substring(
//            url.lastIndexOf("/") + 1)  ).toString()

//        openFile( "$directory" + File.separator+"anish.jpg" )

        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }

        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread(Runnable {
            var downloading = true
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    }
                    lastMsg = msg ?: ""
                }
                cursor.close()
            }
        }).start()
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> openFile( "$directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1)
            ).toString()
            else -> "There's nothing to download"
        }
        return msg
    }



//    private fun openFiles(fileName: String?) {
//        val file = File(fileName+"anish.jpg"
//        )
//        val path = Uri.fromFile(file)
//        val pdfOpenintent = Intent(Intent.ACTION_VIEW)
//        pdfOpenintent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        pdfOpenintent.setDataAndType(path, "*/*")
//        try {
//            startActivity(pdfOpenintent)
//        } catch (e: ActivityNotFoundException) {
//        }
//    }

    private fun openFile(fileName: String?) {
//
//
//        val file = File(fileName)
//        val install = Intent(Intent.ACTION_VIEW)
//        install.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//
//
//
//
//// Old Approach
//        // Old Approach
//        val mimeType = "text/*|application/pdf|image/*\""
////        install.setDataAndType(Uri.fromFile(file), mimeType)
//// End Old approach
//// New Approach
//        // End Old approach
//// New Approach
////        val uri = FileProvider.getUriForFile(
////            requireActivity(),
////            "com.limxtop.research.fileprovider", file
////        )
////        requireActivity(), requireActivity().packageCodePath + ".provider", file
//        val apkURI = FileProvider.getUriForFile(
//            requireActivity(),   "com.jva.provider", file
//        )
////        install.setType("*/*")
//        install.setDataAndType(apkURI, mimeType)
//        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//// End New Approach
//        // End New Approach
//        requireActivity().startActivity(install)


        val intent = Intent()
        intent.action = Intent.ACTION_VIEW

        val uri = Uri.parse("content://com.jva.provider/$fileName")
        intent.setDataAndType(uri, "image/jpeg")
        requireActivity().startActivity(intent)
    }







}
