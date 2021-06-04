package com.bangkit.submissionmade1.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bangkit.submissionmade1.R
import com.bangkit.submissionmade1.core.domain.model.News
import com.bangkit.submissionmade1.databinding.ActivityDetailNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat

class DetailNewsActivity : AppCompatActivity() {

    private val detailNewsViewModel: DetailNewsViewModel by viewModel()
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)

        showDetailNews(detailNews)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            val dateFormat = detailNews.publishedAt
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = format.parse(dateFormat)
            val result = DateFormat.getDateTimeInstance().format(date)
            binding.author.text = detailNews.author
            Glide.with(this).load(detailNews.urlImage).apply(RequestOptions()).error(R.drawable.no_image).into(binding.imgNews)
            binding.title.text = detailNews.title
            binding.dateTimeNews.text = result
            binding.content.text = detailNews.content

            var statusFav = detailNews.isFav
            setStatusFav(statusFav)
            binding.btnFav.setOnClickListener {
                statusFav = !statusFav
                detailNewsViewModel.setFavNews(detailNews, statusFav)
                setStatusFav(statusFav)
            }
        }
    }

    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}