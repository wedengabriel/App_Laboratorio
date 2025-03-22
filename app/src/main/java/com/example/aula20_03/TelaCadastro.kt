package com.example.aula20_03

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast


class TelaCadastro : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textFuncionario = findViewById<EditText>(R.id.Funcionario)
        val textEmail = findViewById<EditText>(R.id.Email)
        val textSenha = findViewById<Button>(R.id.Senha)
        val botaoCadastrarUsuario = findViewById<Button>(R.id.CadastrarUsuario)
        val auth = FirebaseAuth.getInstance()

        botaoCadastrarUsuario.setOnClickListener{

            auth.createUserWithEmailAndPassword(textEmail.text.toString(), textSenha.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener(){
                    Toast.makeText(this, "Nãoo foi possível efetuar o cadastro", Toast.LENGTH_SHORT).show()
                }

            val intent = Intent(this@TelaCadastro, MainActivity::class.java)
            startActivity(intent)

        }
    }
}