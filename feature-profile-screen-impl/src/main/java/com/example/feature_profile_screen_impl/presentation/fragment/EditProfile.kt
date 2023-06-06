package com.example.feature_profile_screen_impl.presentation.fragment


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import coil.compose.rememberImagePainter
import com.example.database.DataBaseRepository
import com.example.feature_profile_screen_impl.data.model.UserProfileModel
import com.example.feature_profile_screen_impl.R
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.feature_profile_screen_impl.presentation.fragment.viewModel.EditProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(user: UserProfileModel, repository: DataBaseRepository, scope: LifecycleCoroutineScope, viewModel: EditProfileViewModel, router: ProfileRouter) {

    val notification = rememberSaveable { mutableStateOf("") }
    if (notification.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_SHORT).show()
        notification.value = ""
    }

    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val newImage = rememberSaveable() {
        mutableStateOf<Uri?>(null)
    }

    val selectedName = rememberSaveable() {
        mutableStateOf((user.sign))
    }

    val signs = listOf("Овен", "Телец", "Близнецы", "Рак",
        "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы")

    val username = rememberSaveable {
        mutableStateOf(user.username)
    }

    val email = rememberSaveable {
        mutableStateOf(user.email)
    }

    val male = rememberSaveable {
        mutableStateOf(male(user.male))
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFF8D7EF),
            Color(0xFFC1D5F5))))) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Пропустить",
                fontSize = 18.sp,
                color = Color(0xFF626161),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        notification.value = "Изменения не сохранились"
                    })
            Text(
                text = "Сохранить",
                fontSize = 18.sp,
                color = Color(0xFF626161),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        notification.value = "Изменения сохранены"
                        scope.launch {
                            if (newImage.value == null) {
                                repository.updateUser(booleanMale(male.value), username.value!!, user.dayOfBirth!!, email.value!!, selectedName.value!!, user.icon!!)
                            }
                            else {
                                repository.updateUser(booleanMale(male.value), username.value!!, user.dayOfBirth!!, email.value!!, selectedName.value!!,
                                    newImage.value!!)
                            }
                            viewModel.editUser(username.value!!, email.value!!, selectedName.value, booleanMale(male.value))
                        }
                        router.openProfile()
                    })
        }
        EditProfileImage(imageUri = imageUri) { updatedUri ->
            newImage.value = updatedUri
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Никнейм:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 8.dp))
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = username.value!!,
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                onValueChange = { username.value = it },
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
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                value = email.value!!,
                onValueChange = { email.value = it},
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 4.dp, top = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Знак Зодиака:",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp,
                color = Color(0xFF626161),
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp))
            selectedName.value?.let {
                Spinner(
                    itemList = signs,
                    selectedItem = it,
                    onItemSelected = { selectedName.value = it },
                )
            }
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
            TextField(
                modifier = Modifier
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.White, Color.White)),
                        shape = RoundedCornerShape(35.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor =  Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = male.value,
                textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                onValueChange = { male.value = it },
            )
        }

    }
}


@Composable
fun EditProfileImage(imageUri: MutableState<Uri?>, onUpdateImageUri: (Uri?) -> Unit) {

    val painter = rememberImagePainter(
        if (imageUri.value == null)
            R.drawable.woman
        else
            imageUri.value
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        uri?.let { updatedUri ->
            imageUri.value = updatedUri
            onUpdateImageUri(updatedUri) // Вызов колбэка onUpdateImageUri для передачи обновленного значения imageUri
        }
    }

    Column(
        modifier = Modifier
            .padding(end = 8.dp, top = 32.dp, start = 8.dp, bottom = 8.dp)
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
        Text(
            text = "Сменить фото",
            fontSize = 18.sp,
            style = MaterialTheme.typography.body2,
            color = Color(0xFF626161),
            modifier = Modifier
                .padding(top = 8.dp))
    }
}

@Composable
fun Spinner(
    itemList: List<String>,
    selectedItem: String,
    onItemSelected: (selectedItem: String) -> Unit
) {
    val expanded = rememberSaveable { mutableStateOf(false) }

    OutlinedButton(
        modifier = Modifier
            .height(55.dp)
            .padding(start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(35.dp),
        onClick = {
            expanded.value = true
        }) {
        Text(
            text = selectedItem,
            color = Color(0xFF626161),
            fontSize = 18.sp,
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(
                1f,
            )
        )
        Icon(
            Icons.Default.ArrowDropDown,
            contentDescription = null
        )
    }
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = {
            expanded.value = false
        }) {
        itemList.forEach {
            DropdownMenuItem(onClick = {
                expanded.value = false
                onItemSelected(it)
            }) {
                Text(text = it)
            }
        }
    }
}

fun male(male: Boolean?): String {
    return if (male == true) {
        "Мужчина"
    }
    else {
        "Женщина"
    }
}

fun booleanMale(male:String): Boolean {
    return if (male == "Мужчина") {
        return true
    }
    else {
        false
    }
}
