package com.carlosjordi.monthlychallenge01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonthlyChallenge01Theme {
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}