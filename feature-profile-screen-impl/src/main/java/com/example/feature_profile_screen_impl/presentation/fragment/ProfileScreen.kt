package com.example.feature_profile_screen_impl.presentation.fragment

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.feature_profile_screen_api.model.UserProfileModel
import com.example.feature_profile_screen_impl.R

@Composable
fun ProfileScreen(
    userProfile: UserProfileModel?,
    navHostController: NavController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFF8D7EF),
            Color(0xFFC1D5F5))))) {
        ProfileImage()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Никнейм:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp))
            Log.e("profile", userProfile.toString())
            Text(
                text = userProfile?.username!!,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Почта:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp))
            Text(
                text = userProfile?.email!!,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Дата рождения:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp))
            Text(
                text = userProfile?.dayOfBirth!!,
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Пол:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp))
            Text(
                text = if (userProfile?.male!!)
                    "Мужчина" else "Женщина",
                style = MaterialTheme.typography.body2,
                color = Color(0xFF626161),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    )
                    .padding(start = 10.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .background(Color.White, shape = RoundedCornerShape(35.dp))
                    .padding(start = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navHostController.navigate(route = Screen.EditProfile.route)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text="Изменить",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF626161),)
            }
        }
    }
}

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable {
        mutableStateOf("")
    }
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.woman
        else
            imageUri.value
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }

    }

    Column(
        modifier = Modifier
            .padding(end = 8.dp, top = 40.dp, start = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
    }
}
