package edu.fer.porttown.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import edu.fer.porttown.R
import edu.fer.porttown.session.SessionManager
import org.koin.android.ext.android.inject

//TODO: do when backend supports deletion, name change
class SettingsFragment : PreferenceFragmentCompat() {
    private val sessionManager by inject<SessionManager>()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferenceScreen.findPreference<Preference>("delete_town")?.onPreferenceClickListener =
            Preference.OnPreferenceClickListener { preference ->
                context?.let {
                    AlertDialog.Builder(it)
                        .setMessage(R.string.are_you_sure)
                        .setPositiveButton(
                            R.string.text_yes,
                            DialogInterface.OnClickListener { _, _ ->
                                Log.d("Dialog", "Delete")
                            })
                        .setNegativeButton(R.string.text_no,
                            DialogInterface.OnClickListener { _, _ ->
                                Log.d("Dialog", "Don't Delete")
                            })
                        .show()
                }
                true
            }
    }

}