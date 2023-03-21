package ru.igorsh.stockview.presentation.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.igorsh.stockview.R

class MainFragment: Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton = view.findViewById<Button>(R.id.main_screen_search_button)
        val profileButton = view.findViewById<Button>(R.id.main_screen_profile_button)
        val newsButton = view.findViewById<Button>(R.id.main_screen_news_button)


        searchButton.setOnClickListener {
            Toast.makeText(activity, "Поиск", Toast.LENGTH_SHORT).show()
        }

        profileButton.setOnClickListener {
            Toast.makeText(activity, "Профиль", Toast.LENGTH_SHORT).show()
        }

        newsButton.setOnClickListener {
            Toast.makeText(activity, "Новости", Toast.LENGTH_SHORT).show()
        }
    }
}