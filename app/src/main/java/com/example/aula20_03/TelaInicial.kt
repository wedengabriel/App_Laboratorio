package com.example.aula20_03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

data class Resultado(
    val vNaOH: Double,
    val n: Double,
    val f: Double,
    val pesoAmostra: Double,
    val resultado: Double
)

class TelaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_inicial)

        // Inicializa Firebase
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()

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
            val pesoAmostra = inputPesoAmostra.text.toString().toDoubleOrNull()

            if (pesoAmostra == null || pesoAmostra == 0.0) {
                textViewResultado.text = "Erro: Peso da amostra não pode ser vazio ou zero!"
            } else {
                val acidoOleico = (vNaOH * n * f * 28.2) / pesoAmostra
                val resultadoFormatado = "%.2f".format(acidoOleico).toDouble()
                textViewResultado.text = "Resultado: %.2f%%".format(acidoOleico)

                // Cria objeto Resultado e envia para o Firestore
                val resultado = Resultado(vNaOH, n, f, pesoAmostra, resultadoFormatado)
                db.collection("resultados")
                    .add(resultado)
                    .addOnSuccessListener {
                        textViewResultado.append("\n(Salvo com sucesso no Firebase!)")
                    }
                    .addOnFailureListener { e ->
                        textViewResultado.append("\nErro ao salvar: ${e.message}")
                    }
            }
        }
    }
}
