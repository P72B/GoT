package de.p72b.redandroid.got.house.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.p72b.redandroid.got.core.R
import de.p72b.redandroid.got.design.ui.theme.AppTheme
import de.p72b.redandroid.got.design.ui.theme.DIMEN_16
import de.p72b.redandroid.got.design.ui.theme.DIMEN_8
import de.p72b.redandroid.got.house.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HouseDetailsActivity : ComponentActivity() {
    private val viewModel: HouseDetailsViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(Constants.EXTRA_HOUSE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(
                    topBar = {
                        TopBar()
                    },
                    content = {
                        Body()
                    }
                )
            }
        }
    }

    @Composable
    private fun TopBar() {
        TopAppBar(
            title = {
                Text(
                    text = getString(R.string.title_house_details),
                    style = MaterialTheme.typography.h1
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackPressed()
                }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.White)
                }
            }
        )
    }

    @Composable
    private fun Body() {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .padding(
                        end = DIMEN_16,
                        start = DIMEN_16
                    )
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                House()
            }
        }
    }

    @Composable
    private fun House() {
        Text(
            modifier = Modifier.padding(top = DIMEN_16),
            text = viewModel.house.name,
            style = MaterialTheme.typography.h2
        )
        viewModel.house.region?.let {
            Text(
                modifier = Modifier.padding(top = DIMEN_8, bottom = DIMEN_16),
                text = it,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}