package com.football.facts.ui.main.home.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.football.facts.R
import com.football.facts.ui.theme.FootballFactsTheme
import com.football.facts.ui.theme.appColors

@Composable
fun CountriesScreen(viewModel: ICountriesViewModel) {
    val model by viewModel.countriesState.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        LazyColumn(Modifier.fillMaxSize()) {
            items(
                items = model.countries,
                key = { it.name }) { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(OnCountryClickedEvent(item))
                        }) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.icon,
                            builder = {
                                if (item.isIconSvg) {
                                    decoder(SvgDecoder(LocalContext.current))
                                }
                                placeholder(R.drawable.ic_home)
                                error(R.drawable.ic_home)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                    )

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.appColors.textPrimary
                    )
                }
            }
        }

        if (model.isProgressVisible) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center),
                color = MaterialTheme.appColors.progressIndicatorColor
            )
        }
    }

}


@Composable
@Preview
fun CountriesScreenLightPreview() {
    FootballFactsTheme(darkTheme = false) {
        CountriesScreen(PreviewCountries())
    }

}


@Composable
@Preview
fun CountriesScreenDarkPreview() {
    FootballFactsTheme(darkTheme = true) {
        CountriesScreen(PreviewCountries())
    }
}