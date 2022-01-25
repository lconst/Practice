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
import com.example.practice.presentation.news.NewsCounter
import com.example.practice.presentation.news.details.NewsDetailsFollowersAdapter.Companion.VISIBLE_FOLLOWERS_NUMBER
import com.example.practice.utils.setImageByResourceName
import com.example.practice.utils.setupToolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {
    private val binding by viewBinding(FragmentNewsDetailsBinding::bind)
    private val compositeDisposable = CompositeDisposable()
    private val repository = PracticeApp.instance.newsRepository
    private val adapter = NewsDetailsFollowersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setupToolbar(binding.toolbar)
        setHasOptionsMenu(true)
        initRecycler()
        getNewsDetails()
    }

    private fun markNewsAsRead(news: News) {
        if (!news.isRead) {
            news.isRead = true
            repository.update(news)
            handleNewsCounter()
        }
    }

    private fun handleNewsCounter() {
        val disposable = NewsCounter.counter
            .take(1)
            .subscribe { count ->
                NewsCounter.counter.onNext(count - 1)
            }
        compositeDisposable.add(disposable)
    }

    private fun setData(news: News) = with(binding) {
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
        followersCount.text = resources.getString(
            R.string.followers_count,
            news.followers.count() - VISIBLE_FOLLOWERS_NUMBER
        )
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    private fun getNewsDetails() {
        val args: NewsDetailsFragmentArgs by navArgs()
        val disposable = repository.getNewsById(args.newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { news ->
                adapter.submitList(news.followers)
                setData(news)
                markNewsAsRead(news)
            }
        compositeDisposable.add(disposable)
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    companion object {
        const val ARG_NEWS_ID_KEY = "newsId"
    }
}
