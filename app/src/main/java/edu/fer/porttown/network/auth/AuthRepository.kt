package edu.fer.porttown.network.auth

import edu.fer.porttown.model.Account
import edu.fer.porttown.model.User
import edu.fer.porttown.network.dto.AuthDtos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class AuthRepository(private val authService: AuthService) {

    suspend fun register(
        username: String,
        password: String,
        email: String,
        townName: String
    ) = flow<AuthResource<Account>> {
        emit(AuthResource.loading(null))
        try {
            val response = authService.register(
                townName,
                AuthDtos.UserDto(username, password, email)
            )
            if (response.isSuccessful && response.body() != null) {
                val account = response.body()!!.run {
                    Account(User(id, this.username, this.email, token), townDto.toTown())
                }
                emit(AuthResource.authenticated(account))
            } else {
                throw IllegalStateException("Failed to verify account.")
            }
        } catch (t: Throwable) {
            emit(AuthResource.error(null, t.message!!))
        }
    }.flowOn(Dispatchers.IO).conflate()

    suspend fun login(
        username: String,
        password: String
    ) = flow<AuthResource<Account>> {
        emit(AuthResource.loading(null))
        try {
            val response = authService.login(AuthDtos.LoginDto(username), password)
            if (response.isSuccessful) {
                val account = response.body()?.let {
                    Account(User(it.id, it.username, it.email, it.token), it.townDto.toTown())
                }
                emit(AuthResource.authenticated(account))
            } else {
                throw IllegalStateException("Failed to verify account.")
            }

        } catch (t: Throwable) {
            emit(AuthResource.error(null, t.message!!))
        }
    }.flowOn(Dispatchers.IO).conflate()
}