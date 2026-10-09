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

class HelloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_helloact)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Находим список и кнопку из XML
        val textList = findViewById<ListView>(R.id.textList)
        val button1 = findViewById<Button>(R.id.button1)

        // Создаём список для хранения элементов
        val myStringArray = ArrayList<String>()

        // Создаём адаптер, связывающий данные с ListView
        val textAdapter = ArrayAdapter(
            this,
            R.layout.item,
            R.id.itemContent,
            myStringArray
        )

        // Подключаем адаптер к списку
        textList.adapter = textAdapter

        // Обрабатываем нажатие кнопки
        button1.setOnClickListener {

            // Создаём название нового элемента
            val newItem = "Элемент ${myStringArray.size + 1}"

            // Добавляем элемент через адаптер
            textAdapter.add(newItem)

            // Выводим сообщение в Logcat
            Log.d("Laba3", "Добавлен элемент: $newItem")
        }
    }
}