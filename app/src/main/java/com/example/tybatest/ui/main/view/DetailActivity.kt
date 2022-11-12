package com.example.tybatest.ui.main.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tybatest.R
import com.example.tybatest.data.model.University
import com.example.tybatest.databinding.ActivityDetailBinding
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val bundle: Bundle? = intent.extras

        bundle?.apply {
            val university = getParcelable("university") ?: University() as University

            binding.tvName.text = university.name.uppercase(Locale.getDefault())

            binding.tvAlphaTwoCode.text = "Alpha two code: ${university.alphaTwoCode}"
            binding.tvCountry.text = "Country: ${university.country}"

            var domains = ""
            university.domains.forEach{domain -> domains = domains.plus("$domain, ")}
            binding.tvDomains.text = "Domains: " + domains

            var webpages = ""
            university.webPages.forEach{webpage -> webpages = webpages.plus("$webpage, ")}
            binding.tvWebPages.text = "Webpages: " + domains

            binding.btnCamera.setOnClickListener{
                openCameraForPhoto()
            }

            binding.btnGallery.setOnClickListener{
                openGalleryForPhoto()
            }
        }
    }

    private fun openCameraForPhoto(){
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // setResult(1, intentCamera)
        startCameraOrActivityForResult.launch(intentCamera)
    }

    private fun openGalleryForPhoto(){
        val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // setResult(2, intentGallery)
        startCameraOrActivityForResult.launch(intentGallery)
    }

    private val startCameraOrActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        val image: Uri? = it.data?.data
        binding.ivPhoto.setImageURI(image)
    }
        /*when (result.resultCode) {
            0 -> {
                val selectedImage: Uri? = result.data?.data
                binding.ivPhoto.setImageURI(selectedImage)
            }
            1 -> {
                val selectedImage: Uri? = result.data?.data
                binding.ivPhoto.setImageURI(selectedImage)
            }
        }*/
}