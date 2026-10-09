package com.example.laba3

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var imageAvatar: ImageView

    // Системный выбор изображения
    private val selectAvatar = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        if (uri != null) {
            imageAvatar.setImageURI(uri)

            Log.d("Laba3", "Пользователь выбрал аватар")
        } else {
            Log.d("Laba3", "Выбор аватара отменён")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

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

        // Находим элементы интерфейса
        val editLogin = findViewById<EditText>(R.id.editLogin)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editFullName = findViewById<EditText>(R.id.editFullName)
        val editBirthDate = findViewById<EditText>(R.id.editBirthDate)

        val radioGender = findViewById<RadioGroup>(R.id.radioGender)

        imageAvatar = findViewById(R.id.imageAvatar)

        val buttonSelectAvatar =
            findViewById<Button>(R.id.buttonSelectAvatar)

        val buttonRegister =
            findViewById<Button>(R.id.buttonRegister)

        // Обработка выбора аватара
        buttonSelectAvatar.setOnClickListener {

            Log.d("Laba3", "Нажата кнопка выбора аватара")

            selectAvatar.launch("image/*")
        }

        // Обработка регистрации
        buttonRegister.setOnClickListener {

            val login = editLogin.text.toString().trim()
            val password = editPassword.text.toString()
            val fullName = editFullName.text.toString().trim()
            val birthDate = editBirthDate.text.toString().trim()

            val selectedGenderId = radioGender.checkedRadioButtonId

            if (
                login.isEmpty() ||
                password.isEmpty() ||
                fullName.isEmpty() ||
                birthDate.isEmpty() ||
                selectedGenderId == -1
            ) {

                Toast.makeText(
                    this,
                    "Заполните все поля",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("Laba3", "Регистрация: заполнены не все поля")

            } else {

                val gender = if (selectedGenderId == R.id.radioMale) {
                    "Мужской"
                } else {
                    "Женский"
                }

                Log.d(
                    "Laba3",
                    "Регистрация: логин=$login, ФИО=$fullName, " +
                            "дата рождения=$birthDate, пол=$gender"
                )

                Toast.makeText(
                    this,
                    "Данные регистрации проверены",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}