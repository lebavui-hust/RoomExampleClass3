package vn.edu.hust.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.edu.hust.roomexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorDao = ColorDatabase.getInstance(application).colorDao()

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val hex = binding.editHex.text.toString()
            val color = Color(name = name, hex = hex)
            lifecycleScope.launch(Dispatchers.IO) {
                val id = colorDao.insert(color)
                Log.v("TAG", "New ID: $id")
                withContext(Dispatchers.Main) {
                    if (id > 0) {
                        Toast.makeText(this@MainActivity, "New record inserted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Insert failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.buttonUpdate.setOnClickListener {
            val id = binding.editId.text.toString().toInt()
            val name = binding.editName.text.toString()
            val hex = binding.editHex.text.toString()
            val color = Color(id, name, hex)
            lifecycleScope.launch(Dispatchers.IO) {
                val ret = colorDao.update(color)
                Log.v("TAG", "ret = $ret")
            }

        }

        binding.buttonDelete.setOnClickListener {
            val id = binding.editId.text.toString().toInt()
            lifecycleScope.launch(Dispatchers.IO) {
                val ret = colorDao.deleteById(id)
                Log.v("TAG", "ret = $ret")
            }
        }

        binding.buttonSelectAll.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val colors = colorDao.getAllColors()
                Log.v("TAG", "Num records: ${colors.size}")
                for (color in colors) {
                    Log.v("TAG", "${color._id} - ${color.name} - ${color.hex}")
                }
            }
        }
    }
}