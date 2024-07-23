package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    LemonadeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    when (currentStep) {
        1 -> {
            LemonTextAndImage(
                textLabelResourceId = R.string.step_one,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.lemon_content_description
            ) {
                currentStep = 2
                squeezeCount = (2..4).random()
                // 3
            }
        }

        2 -> {
            LemonTextAndImage(
                textLabelResourceId = R.string.step_two,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description
            ) {
                squeezeCount--
                // 2
                if (squeezeCount == 0) {
                    // runs only when squeezeCount is 0
                    currentStep = 3
                }
            }
        }

        3 -> {
            LemonTextAndImage(
                textLabelResourceId = R.string.step_three,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.lemon_content_description
            ) {
                currentStep = 4
            }
        }

        4 -> {
            LemonTextAndImage(
                textLabelResourceId = R.string.step_four,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.lemon_content_description
            ) {
                currentStep = 1
            }
        }
    }
}


@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier.padding(top = 16.dp),
            shape = RoundedCornerShape(32.dp),
            onClick = onImageClick
        ) {
            Image(
                painter = painterResource(id = drawableResourceId),
                contentDescription = stringResource(id = contentDescriptionResourceId)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = stringResource(textLabelResourceId),
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview(showBackground = true, name = "LemonTextAndImagePreview", device = "id:pixel_5")
@Composable
fun LemonTextAndImagePreview() {
    LemonadeAppTheme {
        LemonTextAndImage(
            textLabelResourceId = R.string.step_one,
            drawableResourceId = R.drawable.lemon_tree,
            contentDescriptionResourceId = R.string.lemon_content_description
        ) {}
    }
}