package com.example.practice.presentation.news.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.R
import com.example.practice.databinding.FragmentNewsDetailsBinding
import com.example.practice.model.News
import com.example.practice.presentation.MainActivity
import com.example.practice.presentation.news.details.NewsDetailsFollowersAdapter.Companion.VISIBLE_FOLLOWERS_NUMBER
import com.example.practice.utils.setImageByResourceName
import com.example.practice.utils.setupToolbar
import timber.log.Timber

class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {
    private val binding by viewBinding(FragmentNewsDetailsBinding::bind)
    private val news by lazy { getNewsDetails() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setupToolbar(binding.toolbar)
        setHasOptionsMenu(true)
        setData()
        getNewsDetails()
        initRecycler()
    }

    private fun setData() = with(binding) {
        newsTitle.text = news.title
        title.text = news.title
        date.text = news.getDateFormatted()
        organization.text = news.owner
        address.text = news.location
        phone.text = news.phoneNumbers.joinToString(separator = "\n")
        val images = arrayOf(mainImage, smallImage1, smallImage2)
        images.forEachIndexed { index, imageView ->
            imageView.setImageByResourceName(news.imagesResourcesNames[index])
        }
        description.text = news.description
        followersCount.text = "+${news.followers.count() - VISIBLE_FOLLOWERS_NUMBER}"
    }

    private fun initRecycler() {
        binding.recycler.adapter = NewsDetailsFollowersAdapter(news.followers)
    }

    private fun getNewsDetails(): News {
        val args: NewsDetailsFragmentArgs by navArgs()
        return PracticeApp.instance.newsRepository.getNewsById(args.newsId)
    }

    private fun share() {
        Timber.d("Sharing")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_details_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            R.id.share -> share()
        }
        return true
    }

    companion object {
        const val ARG_NEWS_ID_KEY = "newsId"
    }
}
