package com.example.aula20_03

import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textemail = findViewById<EditText>(R.id.email)
        val textsenha = findViewById<EditText>(R.id.senha)
        val botaologin = findViewById<Button>(R.id.login)
        val botaocadastro = findViewById<Button>(R.id.cadastro)

        val auth = FirebaseAuth.getInstance()

        botaologin.setOnClickListener{
            auth.signInWithEmailAndPassword(textemail.text.toString(), textsenha.text.toString())
                .addOnSuccessListener() {
                    Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, TelaInicial::class.java)
                    startActivity(intent)

                }
                .addOnFailureListener(){
                    Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show()
                }
        }

        botaocadastro.setOnClickListener{

            val intent = Intent(this@MainActivity, TelaCadastro::class.java)
            startActivity(intent)

            //auth.createUserWithEmailAndPassword(textemail.text.toString(), textsenha.text.toString())
            //    .addOnSuccessListener {
            //        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
            //    }
            //    .addOnFailureListener(){
            //        Toast.makeText(this, "Nãoo foi possível efetuar o cadastro", Toast.LENGTH_SHORT).show()
            //    }
        }
    }


}