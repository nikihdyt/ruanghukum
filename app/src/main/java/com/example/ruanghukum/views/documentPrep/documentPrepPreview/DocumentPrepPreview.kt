package com.example.ruanghukum.views.documentPrep.documentPrepPreview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruanghukum.R
import com.example.ruanghukum.databinding.FragmentDocumentPrepPreviewBinding
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.URL


class DocumentPrepPreview : Fragment() {

    private var _binding: FragmentDocumentPrepPreviewBinding? = null
    private val binding get() = _binding!!
    private var documentPath: String? = null

    private val SAVE_DOCUMENT_REQUEST_CODE = 42


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentPrepPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        documentPath = arguments?.getString("documentPath")
        Log.d("documentPath", "$documentPath")

        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SAVE_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                documentPath?.let { path ->
                    GlobalScope.launch(Dispatchers.IO) {
                        try {
                            val connection = URL(path).openConnection()
                            connection.connect()

                            val input = connection.getInputStream()
                            val output = requireContext().contentResolver.openOutputStream(uri)

                            val buffer = ByteArray(4 * 1024)
                            var bytesRead: Int

                            while (input.read(buffer).also { bytesRead = it } != -1) {
                                output?.write(buffer, 0, bytesRead)
                            }

                            output?.close()
                            input.close()

                            launch(Dispatchers.Main) {
                                // You can perform UI operations or show a message after successful download
                                Log.d("PDFView", "Document downloaded successfully: $uri")
                            }
                        } catch (e: Exception) {
                            Log.e("PDFView", "Failed to download document: ${e.message}")
                        }
                    }
                }
            }
        }
    }


    private fun setupView() {
        with(binding) {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            btnDownload.setOnClickListener {
                val documentUrl = arguments?.getString("documentPath")
                if (!documentUrl.isNullOrBlank()) {
                    downloadDocument(documentUrl)
                } else {
                    Log.e("PDFView", "Document URL is null or blank.")
                }
            }
        }

        val pdfView = binding.pdfView

        // Retrieve the documentPath from the arguments
        val documentPath = arguments?.getString("documentPath")
//        val documentPath = "https://storage.googleapis.com/bebasss/pdf-documents/579eb039-8534-4fa6-93d6-47a32f5c07ad-output.pdf"

        documentPath?.let { url ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    // Download the PDF from the URL and save it locally
                    val pdfFile = downloadPdf(url)

                    // Load the locally saved PDF using pdfView
                    pdfFile?.let {
                        launch(Dispatchers.Main) {
                            pdfView.fromFile(it)
                                .pageFitPolicy(FitPolicy.WIDTH)
                                .fitEachPage(true)
                                .load()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("PDFView", "Failed to load PDF from URL: ${e.message}")
                }
            }
        }
    }

    private fun downloadPdf(url: String): File? {
        try {
            val connection = URL(url).openConnection()
            connection.connect()

            val input = connection.getInputStream()
            val pdfFile = File(requireContext().filesDir, "downloaded_pdf.pdf")
            val output = FileOutputStream(pdfFile)

            val buffer = ByteArray(4 * 1024)
            var bytesRead: Int

            while (input.read(buffer).also { bytesRead = it } != -1) {
                output.write(buffer, 0, bytesRead)
            }

            output.close()
            input.close()

            return pdfFile
        } catch (e: Exception) {
            Log.e("PDFView", "Failed to download PDF: ${e.message}")
            return null
        }
    }

    private fun downloadDocument(url: String) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf" // Set the MIME type to PDF or the appropriate type
            putExtra(Intent.EXTRA_TITLE, "downloaded_document.pdf")
        }

        startActivityForResult(intent, SAVE_DOCUMENT_REQUEST_CODE)
    }



}