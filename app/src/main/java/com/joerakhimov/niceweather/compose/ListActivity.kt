package com.joerakhimov.niceweather.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joerakhimov.niceweather.R

class ListActivity: ComponentActivity() {

    val items = listOf<String>("Joe", "Azamat", "Aziza")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyList()
        }
    }

    @Composable
    fun MyList() {
        LazyColumn {
            items(items) { item -> ListItem(item) }
        }
    }

    @Composable
    fun ListItem(name: String, modifier: Modifier = Modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.teal_700)
            )
        }
    }

}