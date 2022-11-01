package ammovil.com.excelsior.controller

import ammovil.com.excelsior.R
import ammovil.com.excelsior.databinding.ActivityRegistroBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}