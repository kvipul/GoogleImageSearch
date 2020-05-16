package com.sablania.googleimages

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sablania.googleimages.adapters.GoogleImageAdapter
import com.sablania.googleimages.databinding.ActivitySearchResultBinding
import com.sablania.googleimages.pojos.GoogleImage
import com.sablania.googleimages.viewmodels.GoogleImageViewModel


class SearchResultActivity : AppCompatActivity() {
    companion object{
        fun start(context: Context, searchStr: String) {
            context.startActivity(Intent(context, SearchResultActivity::class.java).apply {
                putExtra(SEARCH_TEXT, searchStr)
            })
        }

        val TAG = SearchResultActivity::class.simpleName
        const val SEARCH_TEXT = "SEARCH_TEXT"
    }

    lateinit var binding : ActivitySearchResultBinding
    lateinit var viewModel : GoogleImageViewModel
    lateinit var adapter : GoogleImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchStr = intent.getStringExtra(SEARCH_TEXT)

        initViewModel()
        initView()
        viewModel.searchGoogleImage(searchStr)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(GoogleImageViewModel::class.java)
        viewModel.getGoogleImageLiveData().observe(this, Observer {
            adapter.list = it.items as ArrayList<GoogleImage>
        })
    }

    private fun initView() {
        binding.apply {
            adapter = GoogleImageAdapter({
                onItemClick(it)
            })
            rvImages.adapter = adapter
            rvImages.layoutManager = LinearLayoutManager(this@SearchResultActivity)
            rvImages.addItemDecoration(DividerItemDecoration(this@SearchResultActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun onItemClick(item: GoogleImage) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(browserIntent)
    }
}
