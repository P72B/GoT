package de.p72b.redandroid.got.core

import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T : Action> ComponentActivity.observeAction(
    liveData: LiveData<T>,
    action: (T) -> Unit
) {
    liveData.observe(this, Observer(action))
}