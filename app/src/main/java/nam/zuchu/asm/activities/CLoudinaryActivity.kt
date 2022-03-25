package nam.zuchu.asm.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cloudinary.android.MediaManager
import com.squareup.picasso.Picasso
import nam.zuchu.asm.databinding.ActivityCloudinaryBinding

class CLoudinaryActivity : AppCompatActivity() {
    private var config = mutableMapOf<Any,Any>()
    private val IMAGE_REQ = 1
    private var imagePath: Uri? = null
    lateinit var cloudinaryBinding: ActivityCloudinaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cloudinaryBinding = ActivityCloudinaryBinding.inflate(layoutInflater)
        setContentView(cloudinaryBinding.root)
        initClick()
        initConfig()
    }

    private fun initConfig() {
        config["cloud_name"] ="dyp8ikyqu"
            config.put("api_key", "141177131558868")
            config.put("api_secret", "yi-CT6D5awMVZWGQ1IZUmXzweOY")
            //        config.put("secure", true);
            MediaManager.init(this, config)
    }

    fun initClick() {
        cloudinaryBinding.ivAvatar.setOnClickListener {
            requestPermission()
                Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            selectImage()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), IMAGE_REQ
            )
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*" // if you want to you can use pdf/gif/video
        intent.action = Intent.ACTION_GET_CONTENT
        someActivityResultLauncher.launch(intent)
    }


    private var someActivityResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data = result.data
            imagePath = data!!.data
            Picasso.get().load(imagePath).into(cloudinaryBinding.ivAvatar)
        }
    }
}