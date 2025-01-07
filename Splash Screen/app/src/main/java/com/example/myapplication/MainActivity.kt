package com.example.myapplication

import android.os.Bundle
import android.view.Surface
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(  color = Color(0xFF36393F), modifier = Modifier.fillMaxSize()) {
                Navigation()
            }
        }
    }
}
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController =  navController, startDestination = "splashScreen") {
        composable ( "splashScreen"){
            SplashScreen(navController = navController)


        }
        composable("main_Screen") {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text(text = "MAIN_SCREEN",color = Color.White)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navController.navigate("main_Screen")


    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id=R.drawable.dis) , contentDescription ="" ,modifier = Modifier.scale(scale.value))

    }

}
