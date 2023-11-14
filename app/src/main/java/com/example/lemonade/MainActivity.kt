package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    var currentScreenState by remember {
        mutableStateOf(1)
    }

    var randomNumberOfClicks by remember {
        mutableStateOf(2)
    }

    when (currentScreenState) {
        1 -> LemonTextAndImage(
            imageResource = R.drawable.lemon_tree,
            contentDescription = R.string.description_lemon_tree,
            textToShow = R.string.lemon_select
        ) {
            currentScreenState = 2
            randomNumberOfClicks = (3..6).random()
        }


        2 -> LemonTextAndImage(
            imageResource = R.drawable.lemon_squeeze,
            contentDescription = R.string.description_lemon,
            textToShow = R.string.lemon_squeeze
        ) {
            randomNumberOfClicks--
            if (randomNumberOfClicks == 0) {
                currentScreenState = 3
            }
        }

        3 -> LemonTextAndImage(
            imageResource = R.drawable.lemon_drink,
            contentDescription = R.string.description_glass_of_lemonade,
            textToShow = R.string.lemon_drink
        ) {
            currentScreenState = 4
        }

        4 -> LemonTextAndImage(
            imageResource = R.drawable.lemon_restart,
            contentDescription = R.string.description_empty_glass,
            textToShow = R.string.lemon_restart
        ) {
            currentScreenState = 1
        }
    }
}

@Composable
fun LemonTextAndImage(
    imageResource: Int,
    contentDescription: Int,
    textToShow: Int,
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onImageClick()
                }
        )
        Spacer(modifier = modifier.height(32.dp))
        Text(text = stringResource(id = textToShow))
    }
}
