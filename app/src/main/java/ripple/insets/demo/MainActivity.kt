package ripple.insets.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ripple.insets.demo.databinding.ActivityMainBinding
import ripple.ui.insets.extensions.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarMain.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(binding.navView)
        }
        enableEdgeToEdge()
    }

}