package com.sablania.googleimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sablania.googleimages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            btnSearch.setOnClickListener {
                val searchStr = tietSearch.text.toString()
                if (searchStr.isEmpty()) {
                    Toast.makeText(this@MainActivity, R.string.please_enter_some_text_to_search, Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                SearchResultActivity.start(this@MainActivity, searchStr)
            }
        }
    }
}
