package com.android.nynews.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.nynews.R
import com.android.nynews.room.Article
import com.android.nynews.ui.detailpage.ArticleDetailActivity
import com.android.nynews.ui.detailpage.ArticleDetailFragment.Companion.ARG_ARTICLE
import com.android.nynews.util.listen
import com.android.nynews.util.loadImageUrl
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Adapter that handles list of Articles
 */
@ExperimentalCoroutinesApi
class NewsAdapter(
    private val articlesList: List<Article>
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: AppCompatTextView? = itemView.findViewById(R.id.txt_title)
        private val txtByline: AppCompatTextView? = itemView.findViewById(R.id.txt_byline)
        private val txtPublishedDate: AppCompatTextView? =
            itemView.findViewById(R.id.txt_published_date)
        private val imgThumb: AppCompatImageView? = itemView.findViewById(R.id.img_thumb)

        fun bind(article: Article) {
            article.let {
                txtTitle?.text = it.title
                txtByline?.text = it.byline
                txtPublishedDate?.text = it.published_date

                it.media.forEach { media ->
                    media.metadata.forEach { mediaMetaData ->
                        imgThumb?.loadImageUrl(mediaMetaData.url)
                    }
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article, parent, false)
                return ItemViewHolder(
                    view
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent).listen { position ->

                ContextCompat.startActivity(
                    parent.context,
                    Intent(parent.context, ArticleDetailActivity::class.java)
                        .putExtra(ARG_ARTICLE, articlesList[position]), null
                )
        }
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(articlesList[position])
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}