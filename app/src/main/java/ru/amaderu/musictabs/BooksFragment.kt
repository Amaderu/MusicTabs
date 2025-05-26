package ru.amaderu.musictabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BooksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adpter = BooksPagerAdapter(listOf(
            BooksTypeFragment("Новое"),
            BooksTypeFragment("Прочитанное")
        ), this)

        val pager = view.findViewById<ViewPager2>(R.id.viewPager)
        pager.adapter = adpter
        val tabs = view.findViewById<TabLayout>(R.id.tabLayout)
        tabs.tabMode = TabLayout.MODE_FIXED // устанавливаем режим fixed
        TabLayoutMediator(tabs, pager){tab, position ->
            tab.text = when (position) {
                0 -> "Новое"
                1 -> "Прочитанное"
                else -> throw IndexOutOfBoundsException()
            }
        }.attach()
    }
}
