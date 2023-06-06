package com.example.fortuneproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.database.DataBaseRepository
import com.example.database.model.UserLocal
import com.example.feature_profile_screen_impl.data.model.UserProfileModel
import com.example.fortuneproject.databinding.ActivityMainBinding
import com.example.fortuneproject.di.Navigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var repository: DataBaseRepository

    private var user: UserLocal? = null

    @Inject
    lateinit var navigator: Navigator
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        repository = DataBaseRepository(this)
        lifecycleScope.launch {
            user = repository.findUser()
        }
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigator.initialize(navController)
        if (user == null) {
            navigator.attachNavController(navController, R.navigation.main_nav_graph)
        }
        else {
            navigator.attachNavController(navController, R.navigation.login_nav_graph)
        }

    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        navController.let {
            navigator.detachNavController(it)
        }
    }
}