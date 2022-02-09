package com.android.nynews.ui.detailpage

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.nynews.R
import com.android.nynews.databinding.ActivityArticleDetailsBinding
import com.android.nynews.ui.detailpage.ArticleDetailFragment.Companion.ARG_ARTICLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * An activity representing a single Article detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * article details are presented side-by-side with a list of articles
 * in a [ArticleListActivity].
 */
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()

        if (savedInstanceState == null) {
            val fragment = ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ARTICLE, intent.getParcelableExtra(ARG_ARTICLE))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.article_detail_container, fragment)
                    .commit()
        }
    }


    private fun initViewBinding() {
        binding = ActivityArticleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}