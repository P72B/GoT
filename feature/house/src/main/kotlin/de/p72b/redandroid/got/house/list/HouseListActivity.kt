package de.p72b.redandroid.got.house.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.p72b.redandroid.got.core.R
import de.p72b.redandroid.got.core.observeAction
import de.p72b.redandroid.got.data.House
import de.p72b.redandroid.got.design.ui.theme.AppTheme
import de.p72b.redandroid.got.design.ui.theme.CircularIndeterminateProgressBar
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
        Surface(
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
                Box(modifier = Modifier.fillMaxSize()) {
                    val items: State<List<House>> = viewModel.items.observeAsState(listOf())
                    List(items = items.value)
                    CircularIndeterminateProgressBar(isDisplayed = viewModel.isLoading.value)
                }
            }
        }
    }

    @Composable
    private fun List(items: List<House>) {
        LazyColumn {
            itemsIndexed(items = items) { index, item ->
                viewModel.onChangeScrollPosition(index)
                if ((index + 1) >= (viewModel.page.value * PAGE_SIZE) && !viewModel.isLoading.value) {
                    viewModel.nextPage()
                }
                ItemCard(item = item, onItemClick = {
                    viewModel.onItemClicked(it)
                })
            }
        }
    }

    @Composable
    private fun ItemCard(item: House, onItemClick: (House) -> Unit) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onItemClick(item) })
                .fillMaxWidth()
                .height(48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
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