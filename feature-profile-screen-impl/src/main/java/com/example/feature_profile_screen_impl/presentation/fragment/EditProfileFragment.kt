package com.example.feature_profile_screen_impl.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.feature_profile_screen_impl.R
import com.example.feature_profile_screen_impl.presentation.theme.ComposeTestingTheme


class EditProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen()
            }
        }
    }

    @Composable
    fun ProfileScreen() {

        ProfileContent()
    }
    @Composable
    fun ProfileContent() {
        val signs = listOf("Овен", "Телец", "Близнецы", "Рак",
            "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы")
        var selectedName by rememberSaveable() {
            mutableStateOf("Sign")
        }
        val notification = rememberSaveable { mutableStateOf("") }
        if (notification.value.isNotEmpty()) {
            Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_SHORT).show()
            notification.value = ""
        }
        var name by rememberSaveable {
            mutableStateOf("default name")
        }
        var username by rememberSaveable {
            mutableStateOf("default username")
        }
        var sign by rememberSaveable {
            mutableStateOf("default sign")
        }


        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFEDE2FB),
                Color(0xFFA98AE1))))) {
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
                        })
            }
            ProfileImage()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 4.dp, top = 40.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Имя:",
                    style = MaterialTheme.typography.body2,
                    fontSize = 20.sp,
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
                    value = name,
                    textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF626161)),
                    onValueChange = { name = it },
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 4.dp, top = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Никнейм:",
                    style = MaterialTheme.typography.body2,
                    fontSize = 20.sp,
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
                    value = username,
                    onValueChange = { username = it},
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 4.dp, top = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Знак Зодиака:",
                    style = MaterialTheme.typography.body2,
                    fontSize = 20.sp,
                    color = Color(0xFF626161),
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 8.dp))
                Spinner(
                    itemList = signs,
                    selectedItem = selectedName,
                    onItemSelected = { selectedName = it },
                )
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
        var expanded by rememberSaveable() {
            mutableStateOf(false)
        }

        OutlinedButton(
            modifier = Modifier
                .height(55.dp)
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(35.dp),
            onClick = {
                expanded = true
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
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            itemList.forEach {
                DropdownMenuItem(onClick = {
                    expanded = false
                    onItemSelected(it)
                }) {
                    Text(text = it)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeTestingTheme {
            ProfileScreen()
        }
    }
}

