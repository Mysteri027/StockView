package ru.igorsh.stockview.presentation.screens.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.igorsh.stockview.R

class LoginFragment : Fragment(R.layout.fragment_login) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailField = view.findViewById<EditText>(R.id.login_screen_email_et)
        val passwordField = view.findViewById<EditText>(R.id.login_screen_password_et)

        val loginButton = view.findViewById<Button>(R.id.login_screen_login_button)
        val registerButton = view.findViewById<TextView>(R.id.login_screen_register_tw)


        loginButton.setOnClickListener {
            if (isInputField(emailField.text.toString()) or isInputField(passwordField.text.toString())) {
                Toast.makeText(activity, "Введите email или пароль.", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun isInputField(inputField: String): Boolean = inputField.isNullOrEmpty()
}