package com.esan.pc2dpaesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtPassword: EditText = findViewById(R.id.txtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnCrear: Button = findViewById(R.id.btnCrearCuenta)

        val db = FirebaseFirestore.getInstance()

        btnCrear.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegistrarActivity::class.java)
            startActivity(intent);
        }
        btnLogin.setOnClickListener {
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()

            db.collection("usuarios").document( correo)
                        .get()
                        .addOnSuccessListener { document ->

                                if (document.data!!["clave"] == clave)
                                {
                                    val toast = Toast.makeText(this, "Acceso Permitido", Toast.LENGTH_LONG)
                                    toast.show()
                                }else{
                                    val toast = Toast.makeText(this, "EL USUARIO Y/O CLAVE NO EXISTE EN EL SISTEMA", Toast.LENGTH_LONG)
                                    toast.show()
                                }
                                Log.d("PC2", "${document.id} => ${document.data}")

                        }
                        .addOnFailureListener { exception ->
                            Log.w("PC2", "Error getting documents: ", exception)
                        }

        }
    }
}