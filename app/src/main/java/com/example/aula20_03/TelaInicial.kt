package com.example.aula20_03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_inicial)

        // Ajuste do layout para se adaptar às bordas da tela
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referenciando os componentes do layout
        val inputVNaOH = findViewById<EditText>(R.id.inputVNaOH)
        val inputN = findViewById<EditText>(R.id.inputN)
        val inputF = findViewById<EditText>(R.id.inputF)
        val inputPesoAmostra = findViewById<EditText>(R.id.inputPesoAmostra)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        // Adicionando a lógica ao botão de cálculo
        btnCalcular.setOnClickListener {
            val vNaOH = inputVNaOH.text.toString().toDoubleOrNull() ?: 0.0
            val n = inputN.text.toString().toDoubleOrNull() ?: 0.0
            val f = inputF.text.toString().toDoubleOrNull() ?: 0.0
            val pesoAmostra = inputPesoAmostra.text.toString().toDoubleOrNull() ?: 1.0

            if (pesoAmostra == 0.0) {
                textViewResultado.text = "Erro: Peso da amostra não pode ser zero!"
            } else {
                val acidoOleico = (vNaOH * n * f * 28.2) / pesoAmostra
                textViewResultado.text = "Resultado: %.2f%%".format(acidoOleico)
            }
        }
    }
}
