package com.example.practice.presentation.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.R
import com.example.practice.databinding.FragmentNewsBinding
import com.example.practice.model.News
import com.example.practice.presentation.MainActivity
import com.example.practice.presentation.news.details.NewsDetailsFragment.Companion.ARG_NEWS_ID_KEY
import com.example.practice.presentation.news.viewmodel.NewViewModelFactory
import com.example.practice.presentation.news.viewmodel.NewsViewModel
import com.example.practice.utils.setupToolbar
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModels {
        NewViewModelFactory(
            PracticeApp.instance.newsRepository,
            PracticeApp.instance.categoryRepository,
        )
    }
    private val adapter by lazy { NewsAdapter(::onNewsClick) }
    private val newsCounter = NewsCounter.counter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setupToolbar(binding.toolBar)
        setHasOptionsMenu(true)
        initRecycler()
        observeNewsByViewModel()
    }

    private fun observeNewsByViewModel() {
        viewModel.newsList.observe(viewLifecycleOwner, { newsList ->
            adapter.submitList(newsList)
            calculateUnreadNews(newsList)
            Toast.makeText(requireContext(), "Total: ${newsList.size}", Toast.LENGTH_SHORT)
                .show()
        })
        viewModel.isDataLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
        })
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    private fun onNewsClick(newsId: Int) {
        findNavController().navigate(
            R.id.action_navigationNewsFragment_to_newsDetails,
            bundleOf(ARG_NEWS_ID_KEY to newsId)
        )
    }

    private fun calculateUnreadNews(newsList: List<News>) {
        Single.fromCallable { newsList.count { news -> !news.isRead } }
            .subscribeOn(Schedulers.computation())
            .subscribe { count -> newsCounter.onNext(count) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> findNavController().navigate(R.id.action_navigationNewsFragment_to_filterFragment)
        }
        return true
    }
}
