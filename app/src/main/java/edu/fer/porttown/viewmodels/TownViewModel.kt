package edu.fer.porttown.viewmodels

import androidx.lifecycle.ViewModel
import edu.fer.porttown.session.SessionManager

class TownViewModel constructor(
    private val sessionManager: SessionManager
) : ViewModel() {
}