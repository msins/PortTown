package edu.fer.porttown.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import edu.fer.porttown.model.Account
import edu.fer.porttown.model.Town
import edu.fer.porttown.model.User
import edu.fer.porttown.network.auth.AuthResource

class SessionManager {
    private var cachedAccount = MediatorLiveData<AuthResource<Account>>()

    fun registerAccount(source: LiveData<AuthResource<Account>>) {
        if (cachedAccount.value != null) {
            cachedAccount.apply {
                addSource(source, Observer { authResource ->
                    this.value = authResource
                })
            }
        }
    }

    fun logOut() {
        cachedAccount.value = AuthResource.logout()
    }

    fun getAccount(): LiveData<AuthResource<Account>> {
        return cachedAccount
    }

    private fun getUser(): User? {
        return cachedAccount.value?.data?.user
    }

    private fun getTown(): Town? {
        return cachedAccount.value?.data?.town
    }

    fun getUserName(): String? {
        return getUser()?.username
    }

    fun getTownName(): String? {
        return getTown()?.name
    }

    fun getTownId(): String? {
        return getTown()?.id
    }

    fun getAuthToken(): String? {
        return getUser()?.token
    }
}