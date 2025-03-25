package com.example.aula20_03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase

class TelaCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botaoVoltar = findViewById<Button>(R.id.voltar)
        val textFuncionario = findViewById<EditText>(R.id.Funcionario)
        val textemail = findViewById<EditText>(R.id.email)
        val textsenha = findViewById<EditText>(R.id.senha)
        val botaoCadastrarUsuario = findViewById<Button>(R.id.CadastrarUsuario)
        val auth = FirebaseAuth.getInstance()

        botaoVoltar.setOnClickListener{

            val intent = Intent(this@TelaCadastro, MainActivity::class.java)
            startActivity(intent)

        }

           /* botaoCadastrarUsuario.setOnClickListener{
                auth.createUserWithEmailAndPassword(textemail.text.toString(), textsenha.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener(){
                        Toast.makeText(this, "Nãoo foi possível efetuar o cadastro", Toast.LENGTH_SHORT).show()
                    }
            }*/
    }
}