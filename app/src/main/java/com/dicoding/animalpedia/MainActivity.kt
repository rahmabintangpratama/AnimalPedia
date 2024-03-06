package com.dicoding.animalpedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvAnimal: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimal = findViewById(R.id.rv_animal)
        rvAnimal.setHasFixedSize(true)

        list.addAll(getListAnimal())
        showRecyclerList()

        val btnMoveWithDataActivity: RecyclerView = findViewById(R.id.rv_animal)
        btnMoveWithDataActivity.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListAnimal(): ArrayList<Animal> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnimal = ArrayList<Animal>()
        for (i in dataName.indices) {
            val animal = Animal(dataName[i], dataDescription[i], dataPhoto.getResourceId(i,-1))
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimal.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimal.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object : ListAnimalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Animal) {
                showSelectedAnimal(data)
            }
        })
    }

    private fun showSelectedAnimal(animal: Animal) {
        val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveIntent.putExtra(DetailActivity.EXTRA_ANIMAL, animal)
        startActivity(moveIntent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rv_animal -> {
                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}