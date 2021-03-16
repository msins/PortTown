package edu.fer.porttown.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import edu.fer.porttown.model.Account
import edu.fer.porttown.network.auth.AuthRepository
import edu.fer.porttown.network.auth.AuthResource
import edu.fer.porttown.session.SessionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class AuthViewModel constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    val observeAuthState: LiveData<AuthResource<Account>> get() = sessionManager.getAccount()

    fun register(username: String, password: String, email: String, townName: String) {
        viewModelScope.launch {
            val source = authRepository.register(username, password, email, townName)
            sessionManager.registerAccount(source.asLiveData())
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val source = authRepository.login(username, password)
            sessionManager.registerAccount(source.asLiveData())
        }
    }
}