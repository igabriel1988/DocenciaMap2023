package com.example.docenciamap2023

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.docenciamap2023.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
        private lateinit var binding:ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView( binding.root )



            binding.loginbtn.setOnClickListener(){
                if(validaLogin(binding.username.text.toString(),binding.password.text.toString())){
                    alert("Login correcto")
                }else{
                    print("!!!!!!!!!!!!!!!!!!!LOGIN INCORRECTO")
                }
                val i = Intent(this,PaginaInicioMainActivity::class.java)
                startActivity(i)
                finish();
            }
        }////Fin funcion onCreate


    private fun validaLogin(usuario:String,clave:String) : Boolean {
        return usuario.trim().lowercase() == clave.trim().lowercase()
    }

    private fun alert(mensaje:String) {
        val bulder = AlertDialog.Builder(this)
        bulder.setTitle("Mensaje")
        bulder.setMessage(mensaje)
        bulder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = bulder.create()
        dialog.show()
    }
 }
