package com.mangatalabs.core_ui.events


sealed class UICoreEvent {
    data class SnackbarEvent(val uiText: String, val uiActionLabel: String? = null) : UICoreEvent()
}