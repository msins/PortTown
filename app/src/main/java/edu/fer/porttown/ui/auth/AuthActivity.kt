package edu.fer.porttown.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import edu.fer.porttown.R
import edu.fer.porttown.databinding.ActivityLoginBinding
import edu.fer.porttown.network.auth.AuthResource
import edu.fer.porttown.ui.game.MainActivity
import edu.fer.porttown.viewmodels.AuthViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class AuthActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    private var isLoginVisible = false
    private var isRegisterVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        authViewModel.observeAuthState.observe(this, Observer {
            when (it.status) {
                AuthResource.AuthStatus.LOADING -> {
                    showProgressBar(true)
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    statusNotification(getString(R.string.logged_out))
                }

                AuthResource.AuthStatus.AUTHENTICATED -> {
                    showProgressBar(false)
                    statusNotification(getString(R.string.authenticated))
                    onLoginSuccess()
                }
                AuthResource.AuthStatus.ERROR -> {
                    showProgressBar(false)
                    it.message?.let { message -> statusNotification(message) }
                }
            }
        })

        binding.authViewModel = authViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onLoginClicked(view: View) {
        if (isRegisterVisible) showRegisterForm(false)
        if (isLoginVisible) return
        showLoginForm()
    }

    fun onRegisterClicked(view: View) {
        if (isRegisterVisible) return
        if (!isLoginVisible) showLoginForm()
        showRegisterForm(true)
    }

    fun onNextClicked(view: View) {
        if (isRegisterVisible) {
            binding.loginForm.apply {
                authViewModel.register(
                    usernameInputText.text.toString(),
                    passwordInputText.text.toString(),
                    emailInputText.text.toString(),
                    townNameInputText.text.toString()
                )
            }
        } else {
            binding.loginForm.apply {
                authViewModel.login(
                    usernameInputText.text.toString(),
                    passwordInputText.text.toString()
                )
            }
        }
    }

    private fun showLoginForm() {
        isLoginVisible = true
        binding.loginForm.root.visibility = View.VISIBLE
    }

    private fun showRegisterForm(visibility: Boolean) {
        isRegisterVisible = visibility
        binding.loginForm.apply {
            val viewVisibility = if (visibility) View.VISIBLE else View.GONE
            townNameInputLayout.visibility = viewVisibility
            emailInputLayout.visibility = viewVisibility
        }
    }

    private fun showProgressBar(visibility: Boolean) {
        binding.verifyingUser.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun statusNotification(statusText: String) {
        Snackbar.make(binding.root, statusText, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        val TAG = AuthActivity::class.java.simpleName
    }
}