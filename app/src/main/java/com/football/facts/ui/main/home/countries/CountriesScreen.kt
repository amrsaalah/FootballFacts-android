package com.football.facts.ui.main.home.countries

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.football.facts.R
import com.football.facts.ui.theme.H1
import com.football.facts.ui.theme.Purple200
import com.football.facts.ui.theme.White

@Composable
fun CountriesScreen(state: CountriesScreenState) {
    val model by state.countriesDisplay.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {

        LazyColumn(Modifier.fillMaxSize()) {
            items(
                items = model.countries,
                key = { it.name }) { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            state.onCountryClicked(item)
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

                    Text(text = item.name, style = H1)
                }
            }
        }

        if (model.isProgressVisible) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center),
                color = Purple200
            )
        }
    }

}


@Composable
@Preview
fun CountriesScreenPreview() {
    CountriesScreen(CountriesScreenState.preview())
}