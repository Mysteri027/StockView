package ru.igorsh.stockview.presentation.screens.registration

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R
import ru.igorsh.stockview.domain.model.User

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: RegistrationViewModel by viewModel()
    private var isPasswordVisible = false

    private lateinit var emailField: TextView
    private lateinit var passwordField: TextView
    private lateinit var visibleEye: ImageView
    private lateinit var registerButton: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUiElements(view)

        registerButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            registerUser(email, password)
        }

        visibleEye.setOnClickListener {
            if (isPasswordVisible) {
                changePasswordFieldVisibility(
                    false,
                    R.drawable.eye,
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                )
            } else {
                changePasswordFieldVisibility(
                    true,
                    R.drawable.eye_off,
                    InputType.TYPE_CLASS_TEXT
                )
            }
        }

    }

    private fun setupUiElements(view: View) {
        emailField = view.findViewById(R.id.register_screen_email_et)
        passwordField = view.findViewById(R.id.register_screen_password_et)
        visibleEye = view.findViewById(R.id.register_screen_password_eye)
        registerButton = view.findViewById(R.id.register_screen_register_button)
    }

    private fun showTextMessage(@StringRes message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun registerUser(email: String, password: String) {
        if (viewModel.isEmptyFields(email, password)) {
            showTextMessage(R.string.empty_field_message)
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.register(User(email, password))
            }

            viewModel.authStatus.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                } else {
                    showTextMessage(R.string.unsuccessful_register)
                }
            }
        }
    }

    private fun changePasswordFieldVisibility(
        currentVisibility: Boolean,
        @DrawableRes resId: Int,
        inputType: Int
    ) {
        isPasswordVisible = currentVisibility
        visibleEye.setImageResource(resId)
        passwordField.inputType = inputType
    }
}