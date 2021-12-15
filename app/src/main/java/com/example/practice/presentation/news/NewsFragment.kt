package com.example.practice.presentation.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentNewsBinding
import com.example.practice.model.News
import com.example.practice.model.NewsCategory
import com.example.practice.presentation.MainActivity
import com.example.practice.utils.setupToolbar
import java.util.Date

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val binding by viewBinding(FragmentNewsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setupToolbar(binding.toolBar)
        setHasOptionsMenu(true)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recycler.adapter = NewsAdapter(createFakeNews())
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

    private fun createFakeNews(): List<News> {
        return listOf(
            News(
                0,
                R.drawable.news_image_1,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                Date(System.currentTimeMillis()),
                NewsCategory.CHILDREN
            ),
            News(
                1,
                R.drawable.news_image_2,
                "Конкурс по вокальному пению в детском доме №6",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                Date(System.currentTimeMillis()),
                NewsCategory.CHILDREN
            )
        )
    }
}
