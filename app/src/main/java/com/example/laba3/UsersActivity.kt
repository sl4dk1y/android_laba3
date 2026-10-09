package com.example.laba3

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_users)

        // Настраиваем отступы для системных панелей Android
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // Получаем элементы интерфейса
        val usersList = findViewById<ListView>(R.id.usersList)
        val buttonAddUser = findViewById<Button>(R.id.buttonAddUser)

        // Создаём список пользователей
        val users = ArrayList<String>()

        // Создаём адаптер для отображения пользователей
        val usersAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            users
        )

        // Подключаем адаптер к ListView
        usersList.adapter = usersAdapter

        // Обрабатываем нажатие кнопки «Добавить»
        buttonAddUser.setOnClickListener {

            // Формируем имя нового пользователя
            val newUser = "Пользователь ${users.size + 1}"

            // Добавляем пользователя в список
            usersAdapter.add(newUser)

            // Записываем событие в Logcat
            Log.d("Laba3", "Добавлен пользователь: $newUser")
        }

        val buttonDeleteUser = findViewById<Button>(R.id.buttonDeleteUser)

        buttonDeleteUser.setOnClickListener {
            if (usersAdapter.count > 0) {
                val lastUser = usersAdapter.getItem(usersAdapter.count - 1)

                usersAdapter.remove(lastUser)

                Log.d("Laba3", "Удалён пользователь: $lastUser")
            } else {
                Toast.makeText(
                    this,
                    "Список пользователей пуст",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}