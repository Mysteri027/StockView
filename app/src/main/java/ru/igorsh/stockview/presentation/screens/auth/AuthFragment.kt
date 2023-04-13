package ru.igorsh.stockview.presentation.screens.auth

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.igorsh.stockview.R

class AuthFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: AuthViewModel by viewModel()
    private var isPasswordVisible = false

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var visibleEye: ImageView
    private lateinit var authButton: Button
    private lateinit var changeStateButton: TextView

    private var currentState = AuthScreenState.LOGIN_SCREEN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUiElements(view)

        authButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (!viewModel.isEmptyFields(email, password)) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.authUser(email, password, currentState)
                }
            } else {
                Toast.makeText(this.context, "Введите логин или пароль", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.authScreenState.observe(viewLifecycleOwner) { state ->
            currentState = state
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            when (state) {
                AuthScreenState.LOGIN_SCREEN -> {
                    authButton.text = "Войти"
                    changeStateButton.text = "У меня нет аккаунта. Зарегистрироваться."
                }

                AuthScreenState.REGISTER_SCREEN -> {
                    authButton.text = "Зарегистрироваться"
                    changeStateButton.text = "У меня уже есть аккаунт. Войти."
                }
            }
        }

        viewModel.authStatus.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_authFragment_to_mainFragment)
            }
        }

        changeStateButton.setOnClickListener {
            when (currentState) {
                AuthScreenState.LOGIN_SCREEN -> {
                    viewModel.setAuthScreenState(AuthScreenState.REGISTER_SCREEN)
                }

                AuthScreenState.REGISTER_SCREEN -> {
                    viewModel.setAuthScreenState(AuthScreenState.LOGIN_SCREEN)
                }
            }
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
    }


    private fun setupUiElements(view: View) {
        emailField = view.findViewById(R.id.login_screen_email_et)
        passwordField = view.findViewById(R.id.login_screen_password_et)
        visibleEye = view.findViewById(R.id.login_screen_password_eye)

        authButton = view.findViewById(R.id.login_screen_login_button)
        changeStateButton = view.findViewById(R.id.login_screen_register_tw)
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