package com.mangatalabs.core_ui.screen.settings

import androidx.lifecycle.ViewModel
import com.mangatalabs.core.util.AppConfiguration

class SettingsViewModel(
    private val appConfiguration: AppConfiguration
): ViewModel() {

    fun getAppVersion() = appConfiguration.VERSION_NAME
    fun getBuyMeACoffeeUrlPath() = "https://www.buymeacoffee.com/tomy1604"
    fun getTmdbUrl() = "https://www.themoviedb.org/"
}