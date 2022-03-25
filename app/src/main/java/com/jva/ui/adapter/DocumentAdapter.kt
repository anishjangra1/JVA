package com.jva.ui.adapter

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jva.data.model.DocumentCenter
import com.jva.data.model.DocumentCenterResponse
import com.jva.databinding.ItemDocumentCenterBinding
import com.jva.ui.viewmodels.JVMViewModel
import com.jva.utils.NetworkResult
import kotlinx.coroutines.NonDisposableHandle.parent
import java.io.File
import kotlin.coroutines.coroutineContext



class DocumentAdapter(context: Context,viewModel: JVMViewModel,
    private val list: List<DocumentCenter>,
    private val onItemClick: (documents: DocumentCenter) -> Unit
) : RecyclerView.Adapter<DocumentAdapter.ViewHolder>() {
     val context= context
    val viewmodel = viewModel
    private var msg: String? = ""
    private var lastMsg = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentAdapter.ViewHolder {
        val binding: ItemDocumentCenterBinding = ItemDocumentCenterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DocumentAdapter.ViewHolder, position: Int) {
        holder.bindData(onItemClick, list?.get(position))
    }

    inner class ViewHolder(val binding: ItemDocumentCenterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            onItemClick: (documents: DocumentCenter) -> Unit,
            documents: DocumentCenter,
        ) {
            binding.ivDownload.setOnClickListener(View.OnClickListener {

                viewmodel._responseDocument.value =documents.document
//                downloadImage(documents.document)

//                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
////                val uri: Uri =   Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
//                val uri: Uri =   Uri.parse( documents.document)
//                val request = DownloadManager.Request(uri)
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                val reference: Long = downloadManager.enqueue(request)
            })
//            documents.remarks?.let {
//                binding.rent.text = documents.remarks
//            }

//

            binding.root.setOnClickListener{
                onItemClick(documents)
            }
        }
    }

}