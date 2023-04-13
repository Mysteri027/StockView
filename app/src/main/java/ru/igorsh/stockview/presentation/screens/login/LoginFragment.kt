package ru.igorsh.stockview.presentation.screens.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
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

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()
    private var isPasswordVisible = false

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var visibleEye: ImageView
    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUiElements(view)

        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            loginUser(email, password)
        }

        visibleEye.setOnClickListener {
            if (isPasswordVisible) {
                changePasswordFieldVisibility(
                    false,
                    R.drawable.eye,
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                )
            } else {
                changePasswordFieldVisibility(true, R.drawable.eye_off, InputType.TYPE_CLASS_TEXT)
            }
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun setupUiElements(view: View) {
        emailField = view.findViewById(R.id.login_screen_email_et)
        passwordField = view.findViewById(R.id.login_screen_password_et)
        visibleEye = view.findViewById(R.id.login_screen_password_eye)

        loginButton = view.findViewById(R.id.login_screen_login_button)
        registerButton = view.findViewById(R.id.login_screen_register_tw)
    }

    private fun showTextMessage(@StringRes message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun loginUser(email: String, password: String) {
        if (viewModel.isEmptyFields(email, password)) {

            Toast.makeText(activity, R.string.empty_field_message, Toast.LENGTH_SHORT).show()
        } else {

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.login(User(email, password))
            }

            viewModel.authStatus.observe(viewLifecycleOwner) {
                if (it) {
                    showTextMessage(R.string.successful_login)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    showTextMessage(R.string.unsuccessful_login)
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