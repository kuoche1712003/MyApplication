package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            MyManager.init(this)
            MyManager.getInstance()
        }catch (e: Exception){
            Log.e("my-test", e.message ?: "init error")
        }

        Log.d("Singleton", "OutSide Context hashCode: " + this.hashCode());


        val emailEditText = findViewById<EditText>(R.id.edit_email)
        val email = MyManager.getInstance().getEmail()
        emailEditText.setText(email, TextView.BufferType.EDITABLE);


        findViewById<Button>(R.id.btn_save)
            .setOnClickListener {
                val email = emailEditText.text.toString()

                MyManager.getInstance().saveEmail(email)
            }

        findViewById<Button>(R.id.btn_next_page)
            .setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java).apply {
                }
                startActivity(intent)
            }

    }
}