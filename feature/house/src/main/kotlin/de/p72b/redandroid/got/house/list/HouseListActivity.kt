package de.p72b.redandroid.got.house.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import de.p72b.redandroid.got.core.R
import de.p72b.redandroid.got.core.observeAction
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.design.ui.theme.AppTheme
import de.p72b.redandroid.got.design.ui.theme.DIMEN_16
import de.p72b.redandroid.got.design.ui.theme.DIMEN_8
import de.p72b.redandroid.got.house.Constants
import de.p72b.redandroid.got.house.details.HouseDetailsActivity
import de.p72b.redandroid.got.house.list.HouseListViewModel.ViewAction.ShowHouseDetails
import org.koin.androidx.viewmodel.ext.android.viewModel

class HouseListActivity : ComponentActivity() {
    private val viewModel: HouseListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Column {
                    Head()
                    Body()
                }
            }
        }
        observeViewAction()
    }

    private fun observeViewAction() {
        observeAction(viewModel.viewAction) { action ->
            when (action) {
                is ShowHouseDetails -> {
                    startActivity(Intent(this, HouseDetailsActivity::class.java).apply {
                        putExtra(Constants.EXTRA_HOUSE, action.item)
                    })
                }
            }
        }
    }

    @Composable
    private fun Head() {
        TopAppBar(
            title = {
                Text(
                    text = getString(R.string.title_house_list),
                    style = MaterialTheme.typography.h1
                )
            }
        )
    }

    @Composable
    private fun Body() {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                HouseList()
            }
        }
    }

    @Composable
    private fun HouseList() {
        Text(
            modifier = Modifier.padding(
                top = DIMEN_16,
                bottom = DIMEN_8,
                end = DIMEN_16,
                start = DIMEN_16
            ),
            text = getString(R.string.sub_title_house_list),
            style = MaterialTheme.typography.h2
        )
        val items: State<List<House>> = viewModel.items.observeAsState(listOf())
        List(items = items.value)
    }

    @Composable
    private fun List(items: List<House>) {
        Column {
            items.forEach { item ->
                Row(item = item, onItemClick = {
                    viewModel.onItemClicked(it)
                })
            }
        }
    }

    @Composable
    private fun Row(item: House, onItemClick: (House) -> Unit) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onItemClick(item) })
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(
                        bottom = DIMEN_8,
                        top = DIMEN_8,
                        end = DIMEN_16,
                        start = DIMEN_16
                    ),
                    text = item.getDisplayedName(),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}