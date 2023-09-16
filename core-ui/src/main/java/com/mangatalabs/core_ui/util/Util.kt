package com.mangatalabs.core_ui.util

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
inline fun <reified T> Flow<T>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collectLatest(action)
        }
    }
}

fun LazyListState.loadMore(buffer: Int = 2) : Boolean {
    val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
    return lastVisibleItemIndex > (layoutInfo.totalItemsCount - buffer)
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.drawLine(): Modifier {
    return this.drawWithContent {
        drawContent()
        drawLine(
            color = Color.Gray,
            strokeWidth = 1f,
            cap = StrokeCap.Square,
            start = Offset.Zero.copy(y = 0f),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

// function to open custom chrome tabs.
fun linkToWebPage(context: Context, uriString: String) {

    val mCustomTabsServiceConnection: CustomTabsServiceConnection?
    var mClient: CustomTabsClient?
    var mCustomTabsSession: CustomTabsSession? = null

    mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(componentName: ComponentName, customTabsClient: CustomTabsClient) {
            //Pre-warming
            mClient = customTabsClient
            mClient?.warmup(0L)
            mCustomTabsSession = mClient?.newSession(null)
        }
        override fun onServiceDisconnected(name: ComponentName) {
            mClient = null
        }
    }
    CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", mCustomTabsServiceConnection)
    val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
        .setShowTitle(true)
        .setInstantAppsEnabled(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(uriString))
}