package ru.igorsh.stockview.presentation.screens.registration

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.model.User

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: RegistrationViewModel by viewModel()
    private var isPasswordVisible = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailField = view.findViewById<TextView>(R.id.register_screen_email_et)
        val passwordField = view.findViewById<TextView>(R.id.register_screen_password_et)
        val visibleEye = view.findViewById<ImageView>(R.id.register_screen_password_eye)
        val registerButton = view.findViewById<TextView>(R.id.register_screen_register_button)


        registerButton.setOnClickListener {

            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (viewModel.isEmptyFields(email, password)) {
                Toast.makeText(activity, R.string.empty_field_message, Toast.LENGTH_SHORT).show()
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

    }
}