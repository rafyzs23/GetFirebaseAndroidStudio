package com.example.getfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.firebaseapplication.FirebaseDataClassAdd
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etPrice: EditText
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etPrice = findViewById(R.id.et_add_price)
        etName = findViewById(R.id.et_add_name)
        ref = FirebaseDatabase.getInstance().getReference("Drink")
    }
    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        val arrayKu = FirebaseDataClassAdd()
        btnSubmit.setOnClickListener {
            arrayKu.name = etName.text.toString()
            arrayKu.price = etPrice.text.toString().toInt()
            val taskPush = ref.push()
            taskPush.setValue(arrayKu)
            startActivity(pindah)
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}