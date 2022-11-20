package com.esan.pc2dpaesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.esan.pc2dpaesan.model.UsuarioModel
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        val etDni: EditText = findViewById(R.id.etDNI)
        val etNombre: EditText = findViewById(R.id.etNombre)
        val etClave: EditText = findViewById(R.id.etClave)
        val etClave2: EditText = findViewById(R.id.etClave2)

        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)

        val db = FirebaseFirestore.getInstance()

        if(etClave.text.toString() != etClave2.text.toString())
        {
            val toast = Toast.makeText(this, "Claves no coinciden", Toast.LENGTH_LONG)
            toast.show()
        }else{

            val usuario = UsuarioModel(etNombre.text.toString(), etClave.text.toString())
            btnRegistrar.setOnClickListener {
                db.collection("usuarios")
                    .document(etDni.text.toString())
                    .set(usuario)
                    .addOnSuccessListener {
                        val intent = Intent(this@RegistrarActivity,LoginActivity::class.java)
                        startActivity(intent);
                    }.addOnFailureListener {
                        val toast = Toast.makeText(this, "Error", Toast.LENGTH_LONG)
                        toast.show()
                    }
            }
        }

    }
}