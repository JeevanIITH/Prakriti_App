package com.example.project_test.ui.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_test.R
import com.example.project_test.databinding.ActivityUploadBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    lateinit var G:Uri
    private lateinit var storageref : StorageReference
    private lateinit var firebaseFirestore : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAll()

        binding.buttonSelectImage.setOnClickListener {
            SelectImage()
        }
        binding.buttonUploadImageUploadPage.setOnClickListener {
            doUpload()
        }



    }
    private fun initAll()
    {
        storageref = FirebaseStorage.getInstance().reference.child("All_Images")
        firebaseFirestore=FirebaseFirestore.getInstance()
    }

    public fun  SelectImage()
    {
        ImagePicker.with(this).start()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK)
        {
            val uri:Uri=data?.data!!
            binding.imageView2.setImageURI(uri)
            G=uri
        }
    }
    public fun doUpload()
    {
        var UploadObject=UploadImage()
        var con=UploadImage.conclass()
        val inputData = contentResolver.openInputStream(G)?.readBytes()
        storageref=storageref.child("Image1")
        storageref.putFile(G).addOnCompleteListener {
            if (it.isSuccessful)
            {
                storageref.downloadUrl.addOnCompleteListener { res1->
                    var url_string=res1.result.toString()
                    var statm="insert into all_images(id,username,user_id,image_url) values(11,'Apple',154,'" + url_string.toString()+ "')"
                    var query_execution=UploadImage.SendImage(con,statm)
                    //query_execution=UploadImage.SendImage(con,statm)
                    if(query_execution.equals(1))
                    {
                        Toast.makeText(this, "failed to upload to server", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this, "Successfully uploaded ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Failed to upload", Toast.LENGTH_SHORT).show()
            }

        }
      

    }

}