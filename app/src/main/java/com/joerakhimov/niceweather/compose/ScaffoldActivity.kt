package com.joerakhimov.niceweather.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.joerakhimov.niceweather.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ScaffoldActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScaffold()
        }
    }

    @Composable
    fun MyScaffold() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val scope: CoroutineScope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            contentColor = colorResource(id = R.color.teal_700),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize().padding(it),
                    ){

                }
            },
            topBar = { MyTopAppBar(scaffoldState = scaffoldState, scope = scope) },
            bottomBar = { MyBottomAppBar() },
            drawerContent = {

            }
        )
    }

    @Composable
    fun MyRow() {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()) {
        }
    }

    @Composable
    fun MyTopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
        val drawerState = scaffoldState.drawerState

        TopAppBar(
            navigationIcon = {
                IconButton(
                    content = {
                        Icon(
                            Icons.Default.Menu,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.hello)
                        )
                    },
                    onClick = {
                        scope.launch { if (drawerState.isClosed) drawerState.open() else drawerState.close() }
                    }
                )
            },
            title = { Text(text = stringResource(id = R.string.app_name), color = Color.White) },
            backgroundColor = colorResource(id = R.color.teal_700)
        )
    }

    @Composable
    fun MyBottomAppBar() {
        BottomAppBar(
            content = {},
            backgroundColor = colorResource(id = R.color.teal_700))
    }

}