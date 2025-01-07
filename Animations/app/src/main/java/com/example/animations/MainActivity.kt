package com.example.animations

import android.health.connect.datatypes.units.Volume
import android.icu.text.ListFormatter.Width
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.CircularArray
import androidx.collection.emptyLongSet
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animations.ui.theme.AnimationsTheme
import kotlinx.coroutines.Delay
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            Greeting()
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize())
            {
                CircularProgressBar(percentage =0.8f , number = 100 )
            }
//            Box(
//                contentAlignment = Alignment.Center,
//               modifier = Modifier.fillMaxSize()
//                   .background(Color(0xFF101010))
//            ){
//                Row (
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .border(1.dp,Color.Green, RoundedCornerShape(10.dp))
//                        .padding(30.dp)
//
//                ){
//                    var volume by remember {
//                        mutableStateOf(0f)
//
//                    }
//                    val barCount =20
//                    MusicKnob(
//                        modifier = Modifier.size(100.dp)
//                    ) {
//                        volume = it
//                    }
//                    Spacer(modifier = Modifier.width(20.dp))
//                        VolumeBar(
//                            modifier = Modifier
//                        .fillMaxWidth()
//                        .height(30.dp),
//                        activeBars = (barCount * volume).roundToInt(),
//                        barCount = barCount
//                    )
//                }
//            }
        }
    }
}



@Composable
fun Greeting() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        tween(
            durationMillis = 1000
        )
    )
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )



    Box(
        modifier = Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase Size")
        }
    }
}

@Composable
fun CircularProgressBar( percentage : Float,
                         number: Int,
                         fontSize : TextUnit = 28.sp,
                         radius : Dp = 50.dp,
                         color : Color = Color.Green,
                         strokeWidth: Dp = 8.dp,
                         animDuration: Int = 2000,
                         animDelay: Int = 0
){

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    var curPercentage = animateFloatAsState(
      targetValue = if(animationPlayed) percentage else 0f,
      animationSpec = tween(
          durationMillis = animDuration,
          delayMillis = animDelay
      )
  )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ){
            Canvas(modifier = Modifier.size(radius * 2f)){
                drawArc(
                    color= color,
                    -90f,
                    360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(),cap = StrokeCap.Round)
                )
            }
            Text(
                text = (curPercentage.value * number).toInt().toString(),
                color = Color.Black,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold

            )
        }
}



//music code from here
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun  MusicKnob(
    modifier: Modifier,
    limitingAngle : Float = 25f,
    onValueChange :(Float) -> Unit
){
    var  rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }
    Image(
        painter = painterResource(id=R.drawable.music_knob),
        contentDescription = "music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width/2f
                centerY= windowBounds.size.height/2f
            }
            .pointerInteropFilter {
                event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX-touchX, centerY-touchY) * (180f/ PI).toFloat()
                when(event.action){
                    MotionEvent.ACTION_DOWN,
                        MotionEvent.ACTION_MOVE ->
                    {
                        if (angle !in - limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle
                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        }
                        else false
                    }
                    else -> false

                    }


            }
            .rotate(rotation)
    )
}

@Composable
fun VolumeBar(    modifier : Modifier,
                  activeBars : Int = 0,
                  barCount : Int = 10){
    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)

        }
        Canvas(modifier = modifier){
            for(i in 0 until barCount){
                drawRoundRect(
                    color = if(i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i*barWidth*2f + barWidth/2f,0f),
                    size = Size(barWidth,constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )

            }



        }

    }
}







