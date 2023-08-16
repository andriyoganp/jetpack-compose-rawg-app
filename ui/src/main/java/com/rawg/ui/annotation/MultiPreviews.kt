package com.rawg.ui.annotation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light Mode", showBackground = true)
annotation class MultiPreviews
