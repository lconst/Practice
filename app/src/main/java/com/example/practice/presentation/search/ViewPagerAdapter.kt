package com.example.practice.presentation.search

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice.model.Charity

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = TABS

    override fun createFragment(position: Int): Fragment {
        var arguments = Bundle()
        when (position) {
            0 -> arguments = bundleOf(SearchPageFragment.DATA_KEY to eventsList)
            1 -> arguments = bundleOf(SearchPageFragment.DATA_KEY to nkoList)
        }
        return SearchPageFragment().also { it.arguments = arguments }
    }

    private val eventsList = listOf(
        Charity("Семейный фестиваль «День радости»"),
        Charity("Осенний Фестиваль Шарлотки"),
        Charity("Благотворительный пленэр «Краски жизни»"),
        Charity("Фестиваль «От сердца к сердцу»"),
        Charity("Фестиваль «День Яблок»"),
        Charity("Мастер-класс «Кошка в книжке»"),
        Charity("Кулинарный фестиваль «Плюшки-ватрушки»"),
        Charity("Выставка фотографий «ОБЪЕКТИВная благотворительность»"),
        Charity("Первая благотворительная выставка-продажа акварелей Т.В. Красновой"),
        Charity("Прямая трансляция концерта Jazz Parking в честь дня рождения фонда «Жизнь как чудо»"),
        Charity("Благотворительный концерт Большого симфонического оркестра"),
        Charity("Благотворительный бал «Художественного центра «Дети Марии»"),
        Charity("Флешмоб «Красный день календаря»"),
        Charity("Выставка «Художественного центра «Дети Марии»"),
        Charity("Турнир по мини-футболу для корпоративных команд «Спорт во благо»")
    )

    private val nkoList = listOf(
        Charity("Созидание"),
        Charity("Провидение"),
        Charity("Интернет-фонд «Помоги.org»"),
        Charity("АиФ «Доброе сердце»"),
        Charity("„Дети Земли“"),
        Charity("«Звезда Милосердия»"),
        Charity("Счастливый мир"),
        Charity("«Милосердие детям»"),
        Charity("\"Конвертик для Бога\""),
        Charity("«Помоги делом»"),
        Charity("«Весна в сердце»"),
        Charity("«Предание»"),
        Charity("Благотворительный фонд \"Артемка\""),
        Charity(" \"ПОМОГИ РЕБЕНКУ.РУ\""),
        Charity("Благотворительный фонд \"РОССПАС\""),
        Charity("Благотворительный фонд \"Восточный\""),
        Charity("Благотворительный фонд \"Радуга\""),
        Charity("Благотворительный фонд \"Это чудо\""),
        Charity("Благотворительный фонд \"Место под Солнцем\"")
    )
}

private const val TABS = 2
