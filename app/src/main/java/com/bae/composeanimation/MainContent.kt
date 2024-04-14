package com.bae.composeanimation

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainContent() {
    Scaffold { paddingValues ->
        Log.d("MainContent","Padding Value : $paddingValues")

    }
}
