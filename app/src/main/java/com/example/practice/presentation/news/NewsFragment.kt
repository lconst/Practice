package com.example.practice.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentNewsBinding
import com.example.practice.model.News
import com.example.practice.model.NewsCategory
import java.util.Date

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val binding by viewBinding(FragmentNewsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recycler.adapter = NewsAdapter(createFakeNews())
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
