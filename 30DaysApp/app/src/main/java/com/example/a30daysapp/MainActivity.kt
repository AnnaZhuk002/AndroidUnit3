package com.example.a30daysapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.data.DaySource
import com.example.a30daysapp.model.Day
import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    _30daysApp()
                }
            }
        }
    }
}

@Composable
private fun AppBar(modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        ){
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun RecipesListView(
    dayRecipe: List<Day>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 50.dp)
    ) {
        items(dayRecipe) { dayRecipe ->
            DaysListItem(
                dayRecipe = dayRecipe
            )
        }
    }
}

@Composable
private fun DaysListItem(
    dayRecipe: Day,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Box(
                modifier = Modifier
//                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .animateContentSize()
                    .height(if (expanded) 410.dp else 320.dp)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }


            ) {
                Text(
                    text = stringResource(dayRecipe.nameRes),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                Image(
                    painter = painterResource(dayRecipe.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(300.dp).fillMaxWidth().padding(top = 60.dp)
                )
                if (expanded) {
                    Text(
                        text = stringResource(dayRecipe.instructionRes),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 310.dp)
                    )
                }
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun _30daysApp() {
    Scaffold(
        topBar = {
            AppBar(modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onSurface)
            )
        }

    ) {
        RecipesListView(DaySource.sources)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    _30DaysAppTheme {
        _30daysApp()
    }
}