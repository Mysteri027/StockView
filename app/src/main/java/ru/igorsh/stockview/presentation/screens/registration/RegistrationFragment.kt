package ru.igorsh.stockview.presentation.screens.registration

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.model.User

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    val viewModel: RegistrationViewModel by viewModel()

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
                    Toast.makeText(activity, R.string.passwords_do_not_match, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val result = viewModel.register(User(email, password))
                    result.addOnCompleteListener {
                        if (result.isSuccessful) {
                            viewModel.setAuthStatus(status = true)
                            findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                        } else {
                            Toast.makeText(
                                activity,
                                R.string.unsuccessful_register,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}