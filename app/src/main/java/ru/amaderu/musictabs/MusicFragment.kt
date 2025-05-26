package ru.amaderu.musictabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MusicFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_music, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listGenre =  resources.getStringArray(R.array.genre).toList()

        // Преобразуем массив строк в список фрагментов
        val fragments = listGenre.map { genre ->
            MusicTypeFragment(genre)
        }

        val musicViewPagerAdapter = MusicPagerAdapter(fragments, this)////musicViewPagerAdapter(getSupportFragmentManager(), this)

        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = musicViewPagerAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)

        for (i in 0..<musicViewPagerAdapter.itemCount) {
            tabLayout.addTab(
                tabLayout.newTab()
                    .setText(listGenre[i])
            )
        }
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = listGenre[position]
        }.attach()

    }
}