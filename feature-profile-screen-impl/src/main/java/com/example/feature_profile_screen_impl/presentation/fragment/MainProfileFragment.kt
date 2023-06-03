package com.example.feature_profile_screen_impl.presentation.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.database.DataBaseRepository
import com.example.feature_profile_screen_api.model.UserProfileModel
import com.example.feature_profile_screen_impl.R
import com.example.feature_profile_screen_impl.domain.EditUserUseCase
import com.example.feature_profile_screen_impl.domain.GetUserUseCase
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponentProvider
import com.example.feature_profile_screen_impl.presentation.fragment.utils.getZodiacSign
import com.example.feature_profile_screen_impl.presentation.fragment.viewModel.EditProfileViewModel
import com.example.feature_profile_screen_impl.presentation.theme.ComposeTestingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainProfileFragment : Fragment() {


    private var userProfile: UserProfileModel? = null

    private var userEditProfile: UserProfileModel? = null

    @Inject
    lateinit var editUserUseCase: EditUserUseCase

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    @Inject
    lateinit var router: ProfileRouter

    private val viewModel: EditProfileViewModel by viewModels {
        EditProfileViewModel.provideFactory(
            getUserUseCase,
            editUserUseCase,
            router
        )
    }

    lateinit var repository: DataBaseRepository

    override fun onAttach(context: Context) {
        val profileComponent  = (requireActivity().application as ProfileScreenComponentProvider)
            .provideProfileScreenComponent()
        profileComponent.injectProfileFragment(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        repository = DataBaseRepository(requireContext())
        lifecycleScope.launch {
            val userBd = repository.findUser()
            userProfile = UserProfileModel(userBd?.username, userBd?.email, userBd?.dayOfBirth, userBd?.male)
            setContentToProfileScreen()
        }
        return ComposeView(requireContext())
    }

    private fun setContentToProfileScreen() {
        userProfile?.let {
            val composeView = ComposeView(requireContext())
            composeView.setContent {
                ProfileScreen(userProfile!!, router)
            }
            requireActivity().setContentView(composeView)
        }
    }

    private fun setContentToEditProfileScreen() {
        userProfile?.let {
            val composeView = ComposeView(requireContext())
            composeView.setContent {
                EditProfileScreen(userProfile!!, repository, lifecycleScope, viewModel, router)
            }
            observeViewModel()
            requireActivity().setContentView(composeView)
        }
    }

    @Composable
    fun ProfileScreen(
        userProfile: UserProfileModel?,
        router: ProfileRouter
    ) {

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
                    text = "Вернуться",
                    fontSize = 18.sp,
                    color = Color(0xFF626161),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            router.openHomeFragment()
                        })
            }
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
                Text(text = "Знак зодиака:",
                    style = MaterialTheme.typography.body2,
                    fontSize = 16.sp,
                    color = Color(0xFF626161),
                    modifier = Modifier
                        .padding(start = 20.dp, bottom = 8.dp))
                Text(
                    text = getZodiacSign(userProfile?.dayOfBirth!!),
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
                        setContentToEditProfileScreen()
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

    private fun observeViewModel() {
        with(viewModel) {
            userLiveData.observe(viewLifecycleOwner) { user ->
                user?.let {
                    userEditProfile = user
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeTestingTheme {
            ProfileScreen(userProfile, router)
        }
    }

}