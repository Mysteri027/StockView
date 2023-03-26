package ru.igorsh.stockview.presentation.screens.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.model.User

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()
    private var isPasswordVisible = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailField = view.findViewById<EditText>(R.id.login_screen_email_et)
        val passwordField = view.findViewById<EditText>(R.id.login_screen_password_et)
        val visibleEye = view.findViewById<ImageView>(R.id.login_screen_password_eye)

        val loginButton = view.findViewById<Button>(R.id.login_screen_login_button)
        val registerButton = view.findViewById<TextView>(R.id.login_screen_register_tw)


        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (viewModel.isEmptyFields(email, password)) {

                Toast.makeText(activity, R.string.empty_field_message, Toast.LENGTH_SHORT).show()
            } else {

                val result = viewModel.login(User(email, password))
                result.addOnCompleteListener {

                    if (result.isSuccessful) {

                        Toast.makeText(activity, R.string.successful_login, Toast.LENGTH_SHORT)
                            .show()
                        viewModel.setAuthStatus(status = true)
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    } else {

                        Toast.makeText(activity, R.string.unsuccessful_login, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        visibleEye.setOnClickListener {
            if (isPasswordVisible) {
                isPasswordVisible = false
                visibleEye.setImageResource(R.drawable.eye)
                passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            } else {
                isPasswordVisible = true
                passwordField.inputType = InputType.TYPE_CLASS_TEXT
                visibleEye.setImageResource(R.drawable.eye_off)
            }
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}