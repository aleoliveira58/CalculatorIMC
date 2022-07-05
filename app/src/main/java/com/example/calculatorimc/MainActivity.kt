package com.example.calculatorimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculatorimc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener {
            if (binding.etPeso.text.toString() != "" && binding.etAltura.text.toString() != "") {
                val peso: Double = binding.etPeso.text.toString().toDouble()
                val altura: Double = binding.etAltura.text.toString().toDouble()
                val imc = peso / (altura * altura)
                val df = DecimalFormat("#.00")
                val imcformact = df.format(imc)

                Toast.makeText(this, "IMC:$imcformact - ${checkIMC(imc)}", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Valores não informados!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkIMC(db: Double): String {
        return when (db) {
            in 0.1..17.49 -> "Você está muito abaixo do seu peso ideal!"
            in 17.1..18.49 -> "Você está abaixo do seu peso ideal!"
            in 18.5..24.99 -> "Parabéns, você está em seu peso ideal!"
            in 25.0..29.99 -> "Sobrepeso - Você está acima do seu peso ideal."
            in 30.0..34.99 -> "Cuidado você está em obesidade de grau I"
            in 35.0..39.99 -> "Cuidado você está em obesidade de grau II (severa)"
            else -> "Cuidado você está em obesidade grau III (mórbida)."
        }
    }

}