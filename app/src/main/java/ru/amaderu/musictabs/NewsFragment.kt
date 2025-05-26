package ru.amaderu.musictabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton

class NewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timer.cancel()
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatButton>(R.id.but).setOnClickListener {
            Timer.badgeDrawable.number = 0
            Timer.badgeDrawable.isVisible = false
        }
    }

    override fun onPause() {
        super.onPause()
        Timer.badgeDrawable.isVisible = true
        Timer.start()
    }
}