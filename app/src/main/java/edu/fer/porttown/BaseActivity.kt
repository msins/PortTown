package edu.fer.porttown

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import edu.fer.porttown.network.auth.AuthResource
import edu.fer.porttown.session.SessionManager
import edu.fer.porttown.ui.auth.AuthActivity
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    protected val sessionManager by inject<SessionManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToSession()
        //TODO check if user was persisted on disk...
        if (sessionManager.getAccount().value == null) {
            sessionManager.logOut()
        }
    }

    private fun subscribeToSession() {
        sessionManager.getAccount().observe(this, Observer { authResource ->
            when (authResource.status) {
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    startAuthActivity()
                }
            }
        })
    }

    private fun startAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}