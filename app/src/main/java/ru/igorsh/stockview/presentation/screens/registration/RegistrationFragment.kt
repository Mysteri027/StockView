package ru.igorsh.stockview.presentation.screens.registration

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.igorsh.stockview.R

class RegistrationFragment : Fragment(R.layout.fragment_registration) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailField = view.findViewById<TextView>(R.id.register_screen_email_et)
        val passwordField = view.findViewById<TextView>(R.id.register_screen_password_et)
        val passwordAgainField = view.findViewById<TextView>(R.id.register_screen_password_again)

        val registerButton = view.findViewById<TextView>(R.id.register_screen_register_button)


        registerButton.setOnClickListener {
            if (isInputField(emailField.text.toString()) or isInputField(passwordField.text.toString()) or isInputField(
                    passwordAgainField.text.toString()
                )
            ) {
                Toast.makeText(activity, "Введите email или пароль.", Toast.LENGTH_SHORT).show()
            } else {
                if (passwordAgainField.text.toString() != passwordField.text.toString()) {
                    Toast.makeText(activity, "Пароли не совпадают.", Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                }
            }
        }
    }

    private fun isInputField(inputField: String): Boolean = inputField.isNullOrEmpty()
}