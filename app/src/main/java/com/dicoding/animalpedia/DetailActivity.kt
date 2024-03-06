package com.dicoding.animalpedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ANIMAL = "extra_animal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val receivedAnimal = intent.getParcelableExtra<Animal>(EXTRA_ANIMAL)

        // Menginisialisasi komponen UI
        val imgAnimal: ImageView = findViewById(R.id.img_animal)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)

        // Memuat gambar menggunakan Glide (jika Anda menggunakan Glide)
        Glide.with(this)
            .load(receivedAnimal?.photo)
            .into(imgAnimal)

        // Mengisi TextView dengan informasi yang sesuai dari objek Animal
        tvName.text = receivedAnimal?.name
        tvDescription.text = receivedAnimal?.description

        val btnShare: Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            shareAnimalInfo(receivedAnimal)
        }
    }

    private fun shareAnimalInfo(animal: Animal?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = "${animal?.name}\n${animal?.description}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share to"))
    }
}