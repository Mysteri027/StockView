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

            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val secondPassword = passwordAgainField.text.toString()

            if (email.isEmpty() or password.isEmpty() or secondPassword.isEmpty()) {
                Toast.makeText(activity, R.string.empty_field_message, Toast.LENGTH_SHORT).show()
            } else {
                if (password != secondPassword) {
                    Toast.makeText(activity, R.string.passwords_do_not_match, Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                }
            }
        }
    }
}