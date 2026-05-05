package ar.com.premates

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ar.com.premates.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarNavegacion()
    }

    private fun configurarNavegacion() {
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        // Conectar bottom nav con navigation component
        binding.bottomNav.setupWithNavController(navController)

        // Mostrar/ocultar bottom nav según la pantalla
        navController.addOnDestinationChangedListener { _, destino, _ ->
            when (destino.id) {
                R.id.loginFragment -> ocultarBarra()
                else -> mostrarBarra()
            }
        }
    }

    private fun mostrarBarra() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    private fun ocultarBarra() {
        binding.bottomNav.visibility = View.GONE
    }
}